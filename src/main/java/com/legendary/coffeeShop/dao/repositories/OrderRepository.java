package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import com.legendary.coffeeShop.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserAAndOrderStatus(User user, OrderStatus status);
}

