package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vsu.cs.CustomExceptions.DataNotValid;
import ru.vsu.cs.CustomExceptions.FailureAuthenticate;
import ru.vsu.cs.DTO.AuthDTO;
import ru.vsu.cs.jwt.JwtService;

@Component
public class AuthenticatinService {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticatinService.class);

    private UserDetailsServiceImpl userDetailsService;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BlackListService blackListService;

    @Autowired
    public AuthenticatinService(
            UserDetailsServiceImpl userDetailsService,
            JwtService jwtService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            BlackListService blackListService
    )
    {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.blackListService = blackListService;
    }
    
    public String authenticate(AuthDTO data) throws FailureAuthenticate, DataNotValid {
        LOG.debug("Method authenticate");
        try {
            UserDetails details = userDetailsService.loadUserByUsername(data.getName());
            blackListService.checkValidEmail(details.getUsername());
            LOG.debug("Details : {}" , details);
            LOG.debug("password Encoded : {}" , details.getPassword());
            LOG.debug("{}", bCryptPasswordEncoder);
            boolean comparison = bCryptPasswordEncoder.matches(data.getPassword(), details.getPassword());
            LOG.debug("comparion : {}", comparison);
            if (!comparison) {
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