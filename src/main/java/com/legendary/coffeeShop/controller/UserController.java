package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.controller.form.UpdatedUserForm;
import com.legendary.coffeeShop.dao.entities.user.User;
import com.legendary.coffeeShop.service.UserService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final ValidationService validationService;

    private final CommonConstants commonConstants;

    @Autowired
    public UserController(UserService userService, ValidationService validationService, CommonConstants commonConstants) {
        this.userService = userService;
        this.validationService = validationService;
        this.commonConstants = commonConstants;
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @PostMapping("/signUp")
    @ResponseBody
    public Status addNewUser(@RequestBody UserForm userForm, HttpServletRequest request) {
        validationService.validateUserForm(userForm);
        userService.createUser(userForm, request.isUserInRole(commonConstants.getAdminPermission()));
        return new Status("User created successfully");
    }

    @PostMapping("/update")
    @ResponseBody
    public Status updateUser(@RequestBody UpdatedUserForm updatedUserForm, HttpServletRequest request) {

        validationService.validateUserForm(updatedUserForm.getUpdatedUserDetails());
        userService.updateUser(updatedUserForm, request.isUserInRole(commonConstants.getAdminPermission()));
        return new Status("User " + updatedUserForm.getUsernameToUpdate() + " updated successfully");
    }


    @PostMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public Status deleteUser(@RequestBody String username) {
        return userService.deleteUser(username);
    }

}
