package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.OrderItem;
import com.legendary.coffeeShop.dao.repositories.OrderItemRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public ResponseEntity removeOrderItem(int orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Could not find order item with id %d", orderItemId));
        }
        orderItemRepository.delete(orderItem);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }

}
