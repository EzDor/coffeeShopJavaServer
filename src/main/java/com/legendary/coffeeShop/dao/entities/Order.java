package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private User user;

    @Column
    private List<Product> products;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "order_status")
    private OderStatus orderStatus;

}
