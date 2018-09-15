package com.legendary.coffeeShop.service;

import org.springframework.stereotype.Service;

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
