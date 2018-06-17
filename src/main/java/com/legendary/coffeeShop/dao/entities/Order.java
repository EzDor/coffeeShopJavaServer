package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_orders", nullable = false)
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

}
