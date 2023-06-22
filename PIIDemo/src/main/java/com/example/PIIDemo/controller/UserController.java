package com.example.PIIDemo.controller;

import com.example.PIIDemo.dto.User;
import com.example.PIIDemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getAllUsers() {
        LOGGER.info(userService.getAllUsers().toString());
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        LOGGER.info(userService.getUser(id).toString());
        return userService.getUser(id);
    }

}

