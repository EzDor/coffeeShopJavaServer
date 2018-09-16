package com.legendary.coffeeShop.controller;


import com.legendary.coffeeShop.service.OrderItemService;
import com.legendary.coffeeShop.service.OrderService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final ValidationService validationService;

    @Autowired
    public CartController(OrderService orderService, OrderItemService orderItemService, ValidationService validationService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.validationService = validationService;
    }

//    @GetMapping("/{username}")
//    @ResponseBody
//    public ResponseEntity getOrder(@PathVariable String username) {
//        try {
//            Order order = orderService.getOrder(username);
//            return ResponseEntity.status(HttpStatus.OK).body(order);
//        }
//        catch (NoSuchElementException err) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
//        }
//    }
//
//
//    @GetMapping("/all/{username}")
//    @ResponseBody
//    public ResponseEntity getAllOrders(@PathVariable String username) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(username));
//        }
//        catch (NoSuchElementException err) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
//        }
//    }
//
//    @PostMapping("/update/{orderId}")
//    @ResponseBody
//    public ResponseEntity updateOrder(@PathVariable int orderId, @RequestBody List<OrderForm> orderForms) {
//        try {
//            for(OrderForm orderForm : orderForms) {
//                validationService.validateOrderForm(orderForm);
//            }
//            orderService.updateOrder(orderId, orderForms);
//            return ResponseEntity.status(HttpStatus.OK).body(String.format("Order No. %s updated successfully", orderId));
//        } catch (NoSuchElementException|IllegalStateException err) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
//        }
//    }
//
//    @PostMapping("/close/{orderId}")
//    @ResponseBody
//    public ResponseEntity closeOrder(@PathVariable int orderId) {
//        try {
//            orderService.setOrderStatus(orderId, OrderStatus.DONE);
//            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
//        } catch (NoSuchElementException err) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
//        }
//
//    }
//
//    @DeleteMapping("/{orderId}")
//    @ResponseBody
//    public ResponseEntity cancelOrder(@PathVariable int orderId) {
//        try {
//            orderService.setOrderStatus(orderId, OrderStatus.CANCELED);
//            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
//        } catch (NoSuchElementException err) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{orderItemId}")
//    @ResponseBody
//    public ResponseEntity removeItem(@PathVariable int orderItemId) {
//        try {
//            orderItemService.removeOrderItem(orderItemId);
//            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
//        } catch (NoSuchElementException err) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
//        }
//    }
}
