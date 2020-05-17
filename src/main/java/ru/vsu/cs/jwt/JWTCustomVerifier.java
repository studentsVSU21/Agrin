package ru.vsu.cs.jwt;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class JWTCustomVerifier {

    @Value("${DEFAULT_SECRET}")
    private String secret;

    private JWSVerifier jwsVerifier;

    public JWTCustomVerifier() {
    }

    public Optional<SignedJWT> check(String token) {
        this.jwsVerifier = this.buildJWSVerifier();
        SignedJWT jwt = createJWS(token);
        if (    Objects.isNull(jwt) ||
                !validSignature.test(jwt) ||
                !isNotExpired.test(jwt)
        )
            return Optional.empty();
        return Optional.of(jwt);
    }

    private Predicate<SignedJWT> isNotExpired = token ->
            getExpirationDate(token).after(Date.from(Instant.now()));

    private Predicate<SignedJWT> validSignature = token -> {
        try {
            return token.verify(this.jwsVerifier);
        } catch (JOSEException e) {
            e.printStackTrace();
            return false;
        }
    };

    private MACVerifier buildJWSVerifier() {
        try {
            return new MACVerifier(secret);
        } catch (JOSEException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SignedJWT createJWS(String token) {
        try {
            return SignedJWT.parse(token);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date getExpirationDate(SignedJWT token) {
        try {
            return token.getJWTClaimsSet()
                    .getExpirationTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
