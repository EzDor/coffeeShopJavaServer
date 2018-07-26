package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.repositories.OrderRepository;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserRepository userRepository;

    public Order getOrder(String username){
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

    private Order prepareOrder(Order order, User user) {
        order.setUser(user);
        order.setCreationTime(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return order;
    }
}
