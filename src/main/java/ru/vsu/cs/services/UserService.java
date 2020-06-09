package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.InfoUserDTO;
import ru.vsu.cs.Entities.Order;
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


    public boolean addOrderToUser(User user, Order order) {
        boolean res = user.getOrders().add(order);
        LOG.debug("User : {}", user);
        userRepository.save(user);
        LOG.debug("User : {}", user);
        return res;
    }

    public void refactorUser(InfoUserDTO infoUserDTO) throws NotFoundById {
        LOG.debug("infoUserDTO : {}", infoUserDTO);
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        if (!optionalUser.isPresent()) {
            throw new NotFoundById("user");
        }
        User user = optionalUser.get();
        user.setFio(infoUserDTO.getFio());
        user.setPhoneNumber(infoUserDTO.getEmail());
        user.setEmail(infoUserDTO.getEmail());
        userRepository.save(user);
    }
}