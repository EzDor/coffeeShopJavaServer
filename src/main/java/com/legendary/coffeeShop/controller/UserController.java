package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.controller.form.UpdateUserForm;
import com.legendary.coffeeShop.service.UserService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Status addNewUser(@RequestBody NewUserForm userForm) {
        try {
            validationService.validateUserForm(userForm);
        } catch (InputMismatchException err) {
            return new Status(err);
        }

        return userService.createUser(userForm);
    }

    @PostMapping("/update")
    @ResponseBody
    public Status updateUser(@RequestBody UpdateUserForm userForm){
        try {
            validationService.validateUserForm(userForm.getUpdatedUserDetails());
        } catch (InputMismatchException err) {
            return new Status(err);
        }
        return userService.updateUser(userForm);
    }

}
