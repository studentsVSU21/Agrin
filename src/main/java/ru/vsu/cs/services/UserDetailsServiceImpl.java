package ru.vsu.cs.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.reposirories.UserRepository;

import javax.persistence.Access;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LOG.debug("load user from repository");

        Optional<User> user = userRepository.findByEmail(s);
        if ( !user.isPresent()) {
            LOG.debug("Not find USER! , {}"  , s);
            throw new UsernameNotFoundException("User don't find!");
        }
        User res = user.get();
        Collection<GrantedAuthority> authorities =  new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                res.getEmail(),
                res.getPassword(),
                authorities
        );

        return userDetails;
    }
}
