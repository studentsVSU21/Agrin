package ru.vsu.cs.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private final JWTCustomSigner jwtCustomSigner;
    private final JWSHeader jwsHeader;

    @Autowired
    public JwtService(JWTCustomSigner jwtCustomSigner, JWSHeader jwsHeader) {
        this.jwtCustomSigner = jwtCustomSigner;
        this.jwsHeader = jwsHeader;
    }

    public String generateToken(String subject, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        SignedJWT signedJWT;
        JWTClaimsSet claimsSet;

        claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("ru.cs.vsu.agrin")
                .expirationTime(new Date(getExpiration()))
                .claim("roles", authorities
                        .stream()
                        .map(GrantedAuthority.class::cast)
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .build();
        signedJWT = new SignedJWT(jwsHeader, claimsSet);

        try {
            signedJWT.sign(jwtCustomSigner.getSigner());
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return signedJWT.serialize();
    }

    private long getExpiration() {
        return new Date().toInstant()
                .plus(Period.ofDays(1))
                .toEpochMilli();
    }
}