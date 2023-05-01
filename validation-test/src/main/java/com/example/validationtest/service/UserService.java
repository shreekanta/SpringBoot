package com.example.validationtest.service;

import com.example.validationtest.dto.UserRequest;
import com.example.validationtest.entity.User;
import com.example.validationtest.exception.UserNotFoundException;
import com.example.validationtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest){
        User user=User.build(0,userRequest.getName(), userRequest.getEmail(),
                userRequest.getMobile(), userRequest.getGender(), userRequest.getAge(), userRequest.getNationality());
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(int id) throws UserNotFoundException {
        User user=userRepository.findByUserId(id);
        if(user!=null){
            return user;
        }else throw new UserNotFoundException("User not found with id :" + id);

    }
}
