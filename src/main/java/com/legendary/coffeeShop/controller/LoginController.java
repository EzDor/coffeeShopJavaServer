package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login_log")
public class LoginController {

    @Autowired
    private CommonConstants commonConstants;

    @GetMapping
    @ResponseBody
    public Status check() {
        System.out.println("Check successful. constans:" + commonConstants.getTestCommonConstants());
        Status status = new Status(Status.OK, "msg");
        return status;
    }
}
