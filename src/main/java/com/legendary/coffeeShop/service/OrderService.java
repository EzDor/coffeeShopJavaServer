package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.dao.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOrder(int id){
        // check if there is open order
        Order order = orderRepository.findByUserAAndOrderStatus(id, OrderStatus.IN_PROGRESS);
        if (order != null) {
            return order;
        }
        return new Order();
    }
}
