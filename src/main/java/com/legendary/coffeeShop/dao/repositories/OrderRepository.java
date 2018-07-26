package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserAndOrderStatus(User user, OrderStatus status);
    List<Order> findByUser(User user);
}

