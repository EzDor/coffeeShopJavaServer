package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Order;
import com.legendary.coffeeShop.dao.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserAAndOrderStatus(int userId, OrderStatus status);
}

