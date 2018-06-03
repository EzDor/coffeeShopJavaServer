package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.dao.entities.UserStatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.InputMismatchException;

@Service
public class ValidationService {

    @Autowired
    private UserRepository userRepository;

    public void validateUserForm(UserForm userForm) {
        if (isEmptyUsernameOrPassword(userForm) || isNotValidUsernameOrPassword(userForm)) {
            throw new InputMismatchException("Username and password are missing or invalid.");
        }
        if (isUserNameAlreadyExists(userForm)) {
            throw new InputMismatchException("Username already exist");
        }
    }

    private boolean isEmptyUsernameOrPassword(UserForm userForm) {
        return StringUtils.isEmpty(userForm.getUsername()) || StringUtils.isEmpty(userForm.getPassword());
    }

    private boolean isNotValidUsernameOrPassword(UserForm userForm) {
        return StringUtils.containsWhitespace(userForm.getUsername()) || StringUtils.containsWhitespace(userForm.getPassword()) || !userForm.getUsername().matches("\\w+");
    }

    private boolean isUserNameAlreadyExists(UserForm userForm) {
        return userRepository.findByUsernameAndStatus(userForm.getUsername().toLowerCase(), UserStatus.ACTIVE) != null;
    }


}
