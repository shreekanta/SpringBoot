package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.UserCred;
import com.example.springsecuritytest.repository.UserCredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoadUsers {
    @Autowired
    private UserCredRepository userCredRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(UserCred user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userCredRepository.save(user);
        return "User added to system!";
    }
}
