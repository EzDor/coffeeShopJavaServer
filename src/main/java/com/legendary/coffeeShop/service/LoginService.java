package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserSatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createNewUser(){

        User user = new User();
        user.setUsername("EzDor");
        user.setStatus(UserSatus.ACTIVE);
        user.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user);
    }
}
