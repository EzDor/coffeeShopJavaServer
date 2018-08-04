package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private CommonConstants commonConstants;

    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity check() {
        System.out.println("Check successful. constants:" + commonConstants.getTestCommonConstants());
        return ResponseEntity.status(HttpStatus.OK).body("msg");

    }

    @GetMapping("/checkAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity checkAdmin() {
        System.out.println("Check successful. constants:" + commonConstants.getTestCommonConstants());
        return ResponseEntity.status(HttpStatus.OK).body("msg");
    }
}
