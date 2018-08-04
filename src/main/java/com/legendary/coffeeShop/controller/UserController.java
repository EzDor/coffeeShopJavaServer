package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.controller.form.UpdateUserForm;
import com.legendary.coffeeShop.service.UserService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.InputMismatchException;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validationService;


    @PostMapping("/signUp")
    @ResponseBody
    public ResponseEntity addNewUser(@RequestBody NewUserForm userForm) {
        try {
            validationService.validateUserForm(userForm);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        return userService.createUser(userForm);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody UpdateUserForm userForm){
        try {
            validationService.validateUserForm(userForm.getUpdatedUserDetails());
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        return userService.updateUser(userForm);
    }

}
