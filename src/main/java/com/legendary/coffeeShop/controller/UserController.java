package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.controller.form.UpdateUserForm;
import com.legendary.coffeeShop.service.UserService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;


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
            userService.createUser(userForm);
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        } catch (NoSuchElementException err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(err.getMessage());
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody UpdateUserForm userForm){
        try {
            validationService.validateUserForm(userForm.getUpdatedUserDetails());
            userService.updateUser(userForm);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        catch (NoSuchElementException | IllegalAccessException err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("User " + userForm.getUsernameToUpdate() + " updated successfully");
    }

}
