package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.reposirories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private AuthDataService authDataService;

    @Autowired
    public void setUserRepository(
            UserRepository userRepository,
            AuthDataService authDataService
    ) {
        this.userRepository = userRepository;
        this.authDataService = authDataService;
    }

    public User getUserById(Long userID) {
        LOG.debug("Sevice get user bu id ");

        Optional<User> user = userRepository.findById(userID);
        LOG.debug("User : {}" , user.get() );
        return user.get();
    }


    public String getRoleUser(){
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        StringBuilder role = new StringBuilder();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            role.append(user.getRoleUser());
        }
        return role.toString();
    }

}
