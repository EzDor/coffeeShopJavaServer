package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.dao.entities.*;
import com.legendary.coffeeShop.dao.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private ProductService productService;

    @Autowired
    private ComponentService componentService;

    public ResponseEntity getOrder(String username){
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + username + " not found");
        }
        // check if there is an open order
        Order order = orderRepository.findByUserAndOrderStatus(user, OrderStatus.IN_PROGRESS);
        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        order = prepareOrder(new Order(), user);
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    public ResponseEntity getAllOrders(String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + username + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findByUser(user));
    }

    public ResponseEntity updateOrder(int orderId, List<OrderForm> ordersForm){
        Order order = orderRepository.findById(orderId);
        if (order == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Could not find order with id %d", orderId));

        List<OrderItem> orderItems = getOrderItems(ordersForm);
        List<OrderItem> currentOrderItems = order.getOrderItems();
        if (currentOrderItems != null) {
            orderItems.addAll(currentOrderItems);
        }
        order.setOrderItems(orderItems);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }

    public ResponseEntity closeOrder(int orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Could not find order with id %d", orderId));
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }

    /*********************************
     * Private Functions
     *********************************/

    private Order prepareOrder(Order order, User user) {
        order.setUser(user);
        order.setCreationTime(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return order;
    }

    private List<OrderItem> getOrderItems(List<OrderForm> ordersForms) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderForm orderForm: ordersForms) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderForm.getPrice());

            Product product = productService.getProduct(orderForm.getProductName());
            orderItem.setProduct(product);

            List<Component> components = new ArrayList<>();
            for (String componentName: orderForm.getComponentsNames()) {
                components.add(componentService.getComponent(componentName));
            }
            orderItem.setComponents(components);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
