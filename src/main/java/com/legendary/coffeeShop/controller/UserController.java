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

        if (!userService.createUser(userForm)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot create user, username " + userForm.getUsername() + " already exist");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody UpdateUserForm userForm){
        try {
            validationService.validateUserForm(userForm.getUpdatedUserDetails());
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        if (!userService.updateUser(userForm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot update user " + userForm.getUsernameToUpdate());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("User " + userForm.getUsernameToUpdate() + " updated successfully");
    }

}
