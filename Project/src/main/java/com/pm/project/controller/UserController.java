package com.pm.project.controller;

import com.pm.project.entity.User;
import com.pm.project.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UUID register(@RequestBody User user) {
        return userService.doRegistration(user);
    }
}
