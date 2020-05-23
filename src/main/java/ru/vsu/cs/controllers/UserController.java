package ru.vsu.cs.controllers;


import com.google.common.net.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.EmailBusy;
import ru.vsu.cs.CustomExceptions.FailureAuthenticate;
import ru.vsu.cs.DTO.AuthDTO;
import ru.vsu.cs.DTO.RegistrationDTO;
import ru.vsu.cs.DTO.ResponseRegistration;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.services.AuthenticatinService;
import ru.vsu.cs.services.RegistrationService;
import ru.vsu.cs.services.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private AuthenticatinService authenticatinService;
    private RegistrationService registrationService;

    @Autowired
    public UserController(UserService userService,
                          AuthenticatinService authenticatinService,
                          RegistrationService registrationService) {
        this.userService = userService;
        this.authenticatinService = authenticatinService;
        this.registrationService = registrationService;
    }

    @GetMapping("/item/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        LOG.debug("Controller : get User by ID");
        LOG.debug("Controller : get User : {}", SecurityContextHolder.getContext().getAuthentication().getDetails());
        LOG.debug("User2 : {}", (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOG.debug("User2 : {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        LOG.debug("User3 : {}", SecurityContextHolder.getContext().getAuthentication().getCredentials());
        LOG.debug("User3 : {}", SecurityContextHolder.getContext().getAuthentication());
        User user = userService.getUserById(id);

        return user;
    }

    @CrossOrigin
    @PostMapping("/registration")
    public ResponseRegistration registration(@RequestBody RegistrationDTO registration, final HttpServletResponse resp) {
        LOG.debug("LOG : {}", registration);
        ResponseRegistration respBody = new ResponseRegistration();
        try {
            registrationService.register(registration);
            respBody.setStatus("success");
            return respBody;
        }
        catch (EmailBusy ex) {
            respBody.setStatus("failure");
            respBody.setMessage(ex.getMessage());
            return respBody;
        }
    }

    @PostMapping("/login")
    public void authUser(@RequestBody AuthDTO auth, final HttpServletResponse resp) {
        LOG.debug("LOG2 : {}", auth);
        try {
            resp.setHeader(HttpHeaders.AUTHORIZATION, String.join(" ","Bearer", authenticatinService.authenticate(auth)));
            resp.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        }
        catch (FailureAuthenticate ex) {
            resp.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Error");
            resp.setHeader("Error", "invalid parametrs");
        }
    }
}