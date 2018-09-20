package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.order.OrderForm;
import com.legendary.coffeeShop.controller.form.order.UpdatedOrderForm;
import com.legendary.coffeeShop.dao.entities.order.Order;
import com.legendary.coffeeShop.dao.entities.order.OrderItem;
import com.legendary.coffeeShop.dao.entities.order.OrderStatus;
import com.legendary.coffeeShop.dao.entities.user.User;
import com.legendary.coffeeShop.dao.repositories.OrderRepository;
import com.legendary.coffeeShop.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;


    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            UserService userService,
            OrderItemService orderItemService) {

        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderItemService = orderItemService;

    }

    public Order getCartOrder(String username) {
        User user = getUser(username);
        Order order = getActiveOrder(user);
        if (order == null) {
            order = new Order();
        }
        return order;
    }

    public List<Order> getUserArchiveOrders(String username) {
        User user = getUser(username);
        return orderRepository.findAllByUserAndOrderStatus(user, OrderStatus.DONE);
    }

    @Transactional
    public void addItemToOrder(OrderForm orderForm, String username) {
        OrderItem newOrderItem = orderItemService.createOrderItem(orderForm);
        User user = getUser(username);
        Order order = getOrCreate(user);
        Set<OrderItem> orderItems = order.getOrderItems();
        orderItems.add(newOrderItem);
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrderItem(UpdatedOrderForm updatedOrderForm, String username) {
        Order order = getAndValidateActiveOrder(username);
        orderItemService.updateOrderItem(updatedOrderForm.getOrderItemId(), updatedOrderForm.getOrderDetails(), order.getOrderItems());
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrderItem(int orderItemId, String username) {
        Order order = getAndValidateActiveOrder(username);
        Set<OrderItem> orderItems = order.getOrderItems();
        orderItemService.deleteOrderItem(orderItemId, orderItems);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (orderItems.isEmpty()) {
            order.setOrderStatus(OrderStatus.CANCELED);
        }
        orderRepository.save(order);
    }

    @Transactional
    public void checkout(String username) {
        Order order = getAndValidateActiveOrder(username);
        Set<OrderItem> orderItems = order.getOrderItems();
        orderItemService.checkout(orderItems);
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus(OrderStatus.DONE);
        orderRepository.save(order);
    }


    /*********************************
     * Private Functions
     *********************************/
    private Order createOrder(User user) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUser(user);
        order.setCreationTime(timestamp);
        order.setUpdateTime(timestamp);
        return order;
    }

    private User getUser(String username) {
        User user = userService.getActiveUser(username);
        if (user == null) {
            throw new NoSuchElementException(String.format("User %s not found", username));
        }
        return user;
    }

    private Order getOrCreate(User user) {
        Order order = getActiveOrder(user);
        if (order == null) {
            order = createOrder(user);
        }

        return order;
    }

    private Order getActiveOrder(User user) {
        return orderRepository.findByUserAndOrderStatus(user, OrderStatus.IN_PROGRESS);
    }

    private Order getAndValidateActiveOrder(String username) {
        User user = getUser(username);
        Order order = getActiveOrder(user);
        if (order == null) {
            throw new IllegalArgumentException("Cannot update order item, order is no found.");
        }
        return order;
    }


}
