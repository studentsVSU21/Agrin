package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.vsu.cs.CustomExceptions.FailureAuthenticate;
import ru.vsu.cs.DTO.AuthDTO;
import ru.vsu.cs.jwt.JwtService;

@Component
public class AuthenticatinService {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticatinService.class);

    private UserDetailsServiceImpl userDetailsService;
    private JwtService jwtService;

    @Autowired
    public AuthenticatinService(UserDetailsServiceImpl userDetailsService,
                                JwtService jwtService)
    {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public String authenticate(AuthDTO data) throws FailureAuthenticate {
        LOG.debug("Method authenticate");
        try {
            UserDetails details = userDetailsService.loadUserByUsername(data.getName());
            LOG.debug("Details : {}" , details);
            if (!data.getPassword().equals(details.getPassword())) {
                throw new FailureAuthenticate();
            }
            return jwtService.generateToken(details.getUsername(), details.getPassword(), details.getAuthorities());
        }
        catch (UsernameNotFoundException ex) {
            LOG.debug("User not found");
            throw new FailureAuthenticate();
        }
    }
}