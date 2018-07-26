package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{username}")
    @ResponseBody
    public Order getOrder(@PathVariable String username) {
        return orderService.getOrder(username);
    }
}
