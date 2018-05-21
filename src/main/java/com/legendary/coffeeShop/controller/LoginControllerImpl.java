package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Api("Merge Routers Service")
@Slf4j
public class LoginControllerImpl implements LoginController {

    @Autowired
    private CommonConstants commonConstants;

    @Override
    public Status check() {
        System.out.println("Check successful. constans:" + commonConstants.getTestCommonConstants());
        Status status = new Status(Status.OK, "msg");
        return status;
    }
}
