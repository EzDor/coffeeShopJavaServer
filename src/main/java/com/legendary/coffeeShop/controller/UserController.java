package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.controller.form.UpdateUserForm;
import com.legendary.coffeeShop.dao.entities.User;
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
    public Status addNewUser(@RequestBody NewUserForm userForm) {
        validationService.validateUserForm(userForm);
        userService.createUser(userForm);
        return new Status("User created successfully");
    }

    @PostMapping("/update")
    @ResponseBody
    public Status updateUser(@RequestBody UpdateUserForm userForm, HttpServletRequest request) {

        NewUserForm newUserForm = userForm.getUpdatedUserDetails();
        newUserForm.setPassword(userForm.getPassword());
        validationService.validateUserForm(newUserForm);
        userService.updateUser(userForm, request.isUserInRole(commonConstants.getAdminPermission()));
        return new Status("User " + userForm.getUsernameToUpdate() + " updated successfully");
    }

    @PostMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public Status giveAdminPermissions(@RequestBody String username) {
        return userService.giveAdminPermissions(username);
    }

    @PostMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public Status deleteUser(@RequestBody String username) {
        return userService.deleteUser(username);
    }

}
