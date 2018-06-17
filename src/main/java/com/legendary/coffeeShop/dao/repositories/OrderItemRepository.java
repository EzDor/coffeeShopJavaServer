package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
