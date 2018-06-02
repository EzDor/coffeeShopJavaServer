package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserSatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.InputMismatchException;

@Service
public class ValidationService {

    @Autowired
    private UserRepository userRepository;

    public void validateUserForm(UserForm userForm) {
        if (isEmptyUsernameOrPassword(userForm)) {
            throw new InputMismatchException("User should contain username and password");
        }
        if (userRepository.findByUsernameAndStatus(userForm.getUsername(), UserSatus.ACTIVE) != null) {
            throw new InputMismatchException("Username already exist");
        }
    }

    private boolean isEmptyUsernameOrPassword(UserForm userForm) {
        return StringUtils.isEmpty(userForm.getUsername()) || StringUtils.isEmpty(userForm.getUsername());
    }

}
