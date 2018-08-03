package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.dao.entities.*;
import com.legendary.coffeeShop.dao.repositories.OrderRepository;
import com.legendary.coffeeShop.dao.repositories.ProductRepository;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProductService productService;

    @Autowired
    private ComponentService componentService;

    public Order getOrder(String username){
        // TODO : use service and not repository
        User user = userRepository.findByUsername(username);
        // check if there is an open order
        Order order = orderRepository.findByUserAndOrderStatus(user, OrderStatus.IN_PROGRESS);
        if (order != null) {
            return order;
        }
        order = prepareOrder(new Order(), user);
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrders(String username) {
        return orderRepository.findByUser(userRepository.findByUsername(username));
    }

    public Status updateOrder(int id, List<OrderForm> ordersForm){
        Order order = orderRepository.findById(id);
        if (order == null)
            return new Status(Status.ERROR, String.format("Could not find order with id %d", id));

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderForm orderForm: ordersForm) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderForm.getPrice());
            String prodName = orderForm.getProductName();
            orderItem.setProduct(productService.getProduct(prodName));
            List<Component> components = new ArrayList<>();
            for (String componentName: orderForm.getComponentsNames()) {
                components.add(componentService.getComponent(componentName));
            }
            orderItem.setComponents(components);
            orderItems.add(orderItem);

        }
        List<OrderItem> currentOrderItems = order.getOrderItems();
        if (currentOrderItems != null) {
            orderItems.addAll(currentOrderItems);
        }
        order.setOrderItems(orderItems);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        return new Status(Status.OK, "Order updated successfully");
    }

    private Order prepareOrder(Order order, User user) {
        order.setUser(user);
        order.setCreationTime(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return order;
    }
}
