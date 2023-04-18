package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.UserCred;
import com.example.springsecuritytest.repository.UserCredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCredRepositoryService implements UserDetailsService {
    @Autowired
    private UserCredRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCred> userInfo= repository.findByName(username);
        return userInfo.map(UserCredUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"+ username));

    }
}
