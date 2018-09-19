package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.order.OrderForm;
import com.legendary.coffeeShop.dao.entities.component.Component;
import com.legendary.coffeeShop.dao.entities.order.OrderItem;
import com.legendary.coffeeShop.dao.entities.product.Product;
import com.legendary.coffeeShop.dao.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final ComponentService componentService;

    public OrderItemService(
            OrderItemRepository orderItemRepository,
            ProductService productService,
            ComponentService componentService) {

        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.componentService = componentService;
    }


    public OrderItem createOrderItem(OrderForm orderForm) {
        OrderItem orderItem = prepareOrderItem(orderForm, new OrderItem());
        //save(orderItem);
        return orderItem;
    }

    public void updateOrderItem(int orderItemId, OrderForm orderForm, Set<OrderItem> orderItems) {
        OrderItem orderItem = getAndValidateOrderItem(orderItemId, orderItems);
        prepareOrderItem(orderForm, orderItem);
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(int orderItemId, Set<OrderItem> orderItems) {
        OrderItem orderItem = getAndValidateOrderItem(orderItemId, orderItems);
        orderItems.remove(orderItem);
        orderItemRepository.delete(orderItem);
    }


    void checkout(Set<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Set<Component> components = orderItem.getComponents();
            componentService.decreaseAmount(components);
        }
        orderItemRepository.saveAll(orderItems);
    }

    void save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    /*********************************
     * Private Functions
     *********************************/

    private Set<Component> getComponentsByType(List<String> componentTypes) {
        Set<Component> components = componentService.getComponentsByType(componentTypes);
        if (componentTypes.size() != components.size()) {
            throw new IllegalArgumentException("Cannot update order item, some components is not exist.");
        }

        return components;
    }

    private OrderItem getAndValidateOrderItem(int orderItemId, Set<OrderItem> orderItems) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId);
        if (!orderItems.contains(orderItem)) {
            throw new IllegalArgumentException("Cannot update order item, this item is not part of the previous order.");
        }

        return orderItem;
    }

    private Product getProduct(String productType) {
        return productService.getProduct(productType);
    }

    private void componentToProductValidation(Product product, Set<Component> components) {
        if (!product.getProductComponents().containsAll(components)) {
            throw new IllegalArgumentException("Cannot update order item, some components is part of this product.");
        }
    }

    private OrderItem prepareOrderItem(OrderForm orderForm, OrderItem orderItem) {
        Set<Component> components = getComponentsByType(orderForm.getComponentsTypes());
        Product product = getProduct(orderForm.getProductType());
        double price = getOrderPrice(product, components);
        componentToProductValidation(product, components);
        orderItem.setComponents(components);
        orderItem.setProduct(product);
        orderItem.setPrice(price);
        return orderItem;
    }

    private double getOrderPrice(Product product, Set<Component> components) {
        double price = product.getPrice();
        for (Component component : components) {
            price += component.getPrice();
        }

        return price;
    }
}
