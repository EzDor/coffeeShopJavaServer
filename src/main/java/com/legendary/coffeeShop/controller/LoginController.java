package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.service.LoginService;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private CommonConstants commonConstants;

    @Autowired
    private LoginService loginService;

    @GetMapping("/check")
    @ResponseBody
    public Status check() {
        System.out.println("Check successful. constans:" + commonConstants.getTestCommonConstants());
        Status status = new Status(Status.OK, "msg");
        return status;
    }

    @GetMapping("/newUser")
    @ResponseBody
    public Status addNewUser(){
        loginService.createNewUser();
        return new Status(Status.OK);
    }
}
