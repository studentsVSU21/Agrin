package ru.vsu.cs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.Entities.User;
import ru.vsu.cs.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {

        LOG.debug("Controller : get User by ID");

        User user = userService.getUserById(id);

        return user;
    }


}
