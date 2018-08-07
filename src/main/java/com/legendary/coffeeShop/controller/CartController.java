package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.service.OrderItemService;
import com.legendary.coffeeShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{username}")
    @ResponseBody
    public ResponseEntity getOrder(@PathVariable String username) {
        return orderService.getOrder(username);
    }


    @GetMapping("/all/{username}")
    @ResponseBody
    public ResponseEntity getAllOrders(@PathVariable String username) {
        return orderService.getAllOrders(username);
    }

    @PostMapping("/update/{orderId}")
    @ResponseBody
    public ResponseEntity updateOrder(@PathVariable int orderId, @RequestBody List<OrderForm> orderForm) {
        return orderService.updateOrder(orderId, orderForm);
    }

    @PostMapping("/close/{orderId}")
    @ResponseBody
    public ResponseEntity closeOrder(@PathVariable int orderId) {
        return orderService.closeOrder(orderId, OrderStatus.DONE);
    }

    @DeleteMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity cancelOrder(@PathVariable int orderId) {
        return orderService.closeOrder(orderId, OrderStatus.CANCELED);
    }

    @DeleteMapping("/{orderItemId}")
    @ResponseBody
    public ResponseEntity removeItem(@PathVariable int orderItemId) {
        return orderItemService.removeOrderItem(orderItemId);
    }
}
