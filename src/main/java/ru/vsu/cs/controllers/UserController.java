package ru.vsu.cs.controllers;


import com.google.common.net.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.FailureAuthenticate;
import ru.vsu.cs.DTO.AuthDTO;
import ru.vsu.cs.DTO.RegistrationDTO;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.services.AuthenticatinService;
import ru.vsu.cs.services.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private AuthenticatinService authenticatinService;

    @Autowired
    public UserController(UserService userService,
                          AuthenticatinService authenticatinService) {
        this.userService = userService;
        this.authenticatinService = authenticatinService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {

        LOG.debug("Controller : get User by ID");

        User user = userService.getUserById(id);

        return user;
    }

    @PostMapping
    public void registration(@RequestBody RegistrationDTO registration, final HttpServletResponse resp) {
        LOG.debug("LOG : {}", registration);
    }

    @PostMapping("/login")
    public void authUser(@RequestBody AuthDTO auth, final HttpServletResponse resp) {
        LOG.debug("LOG2 : {}", auth);
        try {
            resp.setHeader(HttpHeaders.AUTHORIZATION, String.join(" ","Bearer", authenticatinService.authenticate(auth)));
            resp.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        }
        catch (FailureAuthenticate ex) {
            resp.setHeader("Error", "invalid parametrs");
        }
    }
}