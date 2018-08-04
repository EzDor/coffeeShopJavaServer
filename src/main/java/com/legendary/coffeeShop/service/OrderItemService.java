package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.OrderItem;
import com.legendary.coffeeShop.dao.repositories.OrderItemRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Status removeOrderItem(int orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem == null) {
            return new Status(Status.ERROR, String.format("Could not find order item with id %d", orderItemId));
        }
        orderItemRepository.delete(orderItem);
        return new Status(Status.OK, "Order updated successfully");
    }

}
