package ru.vsu.cs.controllers;


import com.google.common.net.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.DataNotValid;
import ru.vsu.cs.CustomExceptions.EmailBusy;
import ru.vsu.cs.CustomExceptions.FailureAuthenticate;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.*;
import ru.vsu.cs.DTO.mappers.InfoUserMapper;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.services.AuthDataService;
import ru.vsu.cs.services.AuthenticatinService;
import ru.vsu.cs.services.RegistrationService;
import ru.vsu.cs.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private AuthenticatinService authenticatinService;
    private RegistrationService registrationService;
    private AuthDataService authDataService;
    private InfoUserMapper infoUserMapper;

    @Autowired
    public UserController(UserService userService,
                          AuthenticatinService authenticatinService,
                          RegistrationService registrationService,
                          AuthDataService authDataService,
                          InfoUserMapper infoUserMapper) {
        this.userService = userService;
        this.authenticatinService = authenticatinService;
        this.registrationService = registrationService;
        this.authDataService = authDataService;
        this.infoUserMapper = infoUserMapper;
    }

    @GetMapping("/item/{id}")
    @CrossOrigin
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

    @PostMapping("/registration")
    @CrossOrigin
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

    @PostMapping("/registration/operator")
    @CrossOrigin
    public ResponseRegistration registrationOperator(@RequestBody RegistrationDTO registration) {
        LOG.debug("LOG : {}", registration);
        ResponseRegistration respBody = new ResponseRegistration();
        try {
            registrationService.registerOperator(registration);
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
    @CrossOrigin
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
        catch (DataNotValid ex) {
            resp.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Error");
            resp.setHeader("Error", "BlackList");
        }
    }

    @GetMapping("/role")
    @CrossOrigin
    public RoleResponse getRole() {
        return new RoleResponse(userService.getRoleUser());
    }

    @GetMapping("/info")
    @CrossOrigin
    public InfoUserDTO getInfoForUser() {
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        return optionalUser.map(infoUserMapper::toDto).orElse(null);
    }

    @PostMapping("/refactor")
    @CrossOrigin
    public StatusResponse refactorInfoUser(@RequestBody InfoUserDTO infoUserDTO) {
        LOG.debug("refactor");
        try {
            userService.refactorUser(infoUserDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById notFoundById) {
            LOG.error("Dont found user", notFoundById);
            return StatusResponse.failureResponse();
        }
    }
}