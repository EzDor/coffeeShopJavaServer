package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.OrderItem;
import com.legendary.coffeeShop.dao.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderItemService {

//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Autowired
//    private ComponentService componentService;
//
//    /**
//     * Delete OrderItem row
//     */
//    public void removeOrderItem(int orderItemId) {
//        OrderItem orderItem = orderItemRepository.findById(orderItemId);
//        if (orderItem == null) {
//            throw new NoSuchElementException(String.format("Could not find order item with id %d", orderItemId));
//        }
//        orderItemRepository.delete(orderItem);
//    }
//
//
//    public void decreaseAmount(OrderItem orderItem) {
//        List<Component> components = orderItem.getComponents();
//        for (Component component: components) {
//            componentService.decreaseAmount(component);
//        }
//
//    }
}
