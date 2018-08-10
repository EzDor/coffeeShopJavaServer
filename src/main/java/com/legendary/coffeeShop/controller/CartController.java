package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.service.OrderItemService;
import com.legendary.coffeeShop.service.OrderService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/{username}")
    @ResponseBody
    public ResponseEntity getOrder(@PathVariable String username) {
        try {
            Order order = orderService.getOrder(username);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }


    @GetMapping("/all/{username}")
    @ResponseBody
    public ResponseEntity getAllOrders(@PathVariable String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(username));
        }
        catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }

    @PostMapping("/update/{orderId}")
    @ResponseBody
    public ResponseEntity updateOrder(@PathVariable int orderId, @RequestBody List<OrderForm> orderForms) {
        try {
            for(OrderForm orderForm : orderForms) {
                validationService.validateOrderForm(orderForm);
            }
            orderService.updateOrder(orderId, orderForms);
            return ResponseEntity.status(HttpStatus.OK).body("Order with id " + orderId + "updated successfully");
        } catch (NoSuchElementException|IllegalStateException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PostMapping("/close/{orderId}")
    @ResponseBody
    public ResponseEntity closeOrder(@PathVariable int orderId) {
        try {
            orderService.closeOrder(orderId, OrderStatus.DONE);
            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }

    }

    @DeleteMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity cancelOrder(@PathVariable int orderId) {
        try {
            orderService.closeOrder(orderId, OrderStatus.CANCELED);
            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }

    @DeleteMapping("/{orderItemId}")
    @ResponseBody
    public ResponseEntity removeItem(@PathVariable int orderItemId) {
        try {
            orderItemService.removeOrderItem(orderItemId);
            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }
}
