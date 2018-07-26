package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.service.OrderService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


    @GetMapping("/all/{username}")
    @ResponseBody
    public List<Order> getAllOrders(@PathVariable String username) {
        return orderService.getAllOrders(username);
    }

    @PostMapping("/update/{orderId}")
    @ResponseBody
    public Status updateOrder(@PathVariable int id, @RequestBody List<OrderForm> orderForm) {
        return orderService.updateOrder(id, orderForm);
    }
}
