package ru.vsu.cs.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vsu.cs.CustomExceptions.EmailBusy;
import ru.vsu.cs.DTO.RegistrationDTO;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.reposirories.UserRepository;

import java.util.Optional;

@Component
public class RegistrationService {

    private final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(RegistrationDTO registrationDTO) throws EmailBusy {
        Optional<User> optionalUser =  userRepository.findByEmail(registrationDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailBusy("Email is busy");
        }
        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setFio(registrationDTO.getFio());
        user.setPhoneNumber(registrationDTO.getPhoneNumber());
        user.setRoleUser("ADMIN");
        LOG.debug("crypt : {}", bCryptPasswordEncoder);
        bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        LOG.debug("Password : {}", user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(user);
    }

}
