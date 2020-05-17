package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.reposirories.UserRepository;

import java.util.Optional;

@Component
public class AuthDataService {

    private final Logger LOG = LoggerFactory.getLogger(AuthDataService.class);

    private UserRepository userRepository;

    @Autowired
    public AuthDataService(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserFromUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getDetails();
        return userRepository.findByEmail(user.getUsername());
    }

}
