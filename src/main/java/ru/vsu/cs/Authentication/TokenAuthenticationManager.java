package ru.vsu.cs.Authentication;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.vsu.cs.jwt.JWTCustomVerifier;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    private final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationManager.class);

    private JWTCustomVerifier jwtCustomVerifier;

    @Autowired
    public TokenAuthenticationManager(
            JWTCustomVerifier jwtCustomVerifier
    )
    {
        this.jwtCustomVerifier = jwtCustomVerifier;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            TokenAuthentication auth = (TokenAuthentication) authentication;
            LOG.debug("auth : {}", auth);
            Optional<SignedJWT> optionalJWT = jwtCustomVerifier.check(auth.getToken());
            if ( optionalJWT.isPresent() )
            {
                SignedJWT jwt = optionalJWT.get();
                try {
                    JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
                    Collection<GrantedAuthority> authorities = parseRoles((String) jwtClaimsSet.getClaim("roles"));
                    String userName = jwtClaimsSet.getSubject();
                    auth.setPrincipal(
                            new User(userName,"null", authorities)
                    );
                    LOG.debug("Authorities : {}", authorities);
                    auth.setAuthorities(authorities);
                    return (Authentication) auth;
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new AuthenticationException("Not Parser Token"){};
                }
            }
        }
        throw new AuthenticationException("Not Token"){};
    }

    private Collection<GrantedAuthority> parseRoles(String roles) {
        return Arrays.stream(roles.split(","))
                .map( SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(LinkedList::new))
        ;
    }
}
