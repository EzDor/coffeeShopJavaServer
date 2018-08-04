package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.service.OrderItemService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {


    @Autowired
    private OrderItemService orderItemService;


    @DeleteMapping("/{orderItemId}")
    @ResponseBody
    public Status cancelOrder(@PathVariable int orderItemId) {
        return orderItemService.deleteOrderItem(orderItemId);
    }

}
