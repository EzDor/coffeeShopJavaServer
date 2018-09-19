package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.controller.form.order.CreditCardForm;
import com.legendary.coffeeShop.controller.form.order.OrderForm;
import com.legendary.coffeeShop.controller.form.order.UpdatedOrderForm;
import com.legendary.coffeeShop.dao.entities.order.Order;
import com.legendary.coffeeShop.service.OrderService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("cart")
public class CartController {

    private final OrderService orderService;

    private final ValidationService validationService;

    @Autowired
    public CartController(OrderService orderService, ValidationService validationService) {
        this.orderService = orderService;
        this.validationService = validationService;
    }


    @GetMapping
    @ResponseBody
    public Order getActiveOrder(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return orderService.getCartOrder(username);
    }


    @PostMapping("add")
    @ResponseBody
    public Status addItemToOrder(@RequestBody OrderForm orderForm, HttpServletRequest request) {
        String username = request.getRemoteUser();
        validationService.validateOrderForm(orderForm);
        orderService.addItemToOrder(orderForm, username);
        return new Status("Item is added to cart successfully");
    }

    @GetMapping("archive")
    @ResponseBody
    public List<Order> getArchiveOrders(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return orderService.getUserArchiveOrders(username);
    }

    @PostMapping("update")
    @ResponseBody
    public Status updateOrderItem(@RequestBody UpdatedOrderForm updatedOrderForm, HttpServletRequest request) {
        String username = request.getRemoteUser();
        orderService.updateOrderItem(updatedOrderForm, username);
        return new Status("Item is updated to cart successfully");
    }

    @PostMapping("delete")
    @ResponseBody
    public Status deleteOrderItem(@RequestBody int orderItemId, HttpServletRequest request) {
        String username = request.getRemoteUser();
        orderService.deleteOrderItem(orderItemId, username);
        return new Status("Item is deleted to cart successfully");
    }

    @PostMapping("checkout")
    @ResponseBody
    public Status checkout(@RequestBody CreditCardForm creditCardForm, HttpServletRequest request){
        validationService.validateCreditCardForm(creditCardForm);
        String username = request.getRemoteUser();
        orderService.checkout(username);
        return new Status("Checkout cart successfully");
    }
}
