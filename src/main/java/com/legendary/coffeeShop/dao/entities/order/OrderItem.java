package com.legendary.coffeeShop.dao.entities.order;

import com.legendary.coffeeShop.dao.entities.product.Product;
import com.legendary.coffeeShop.dao.entities.component.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "order_item_components")
    private List<Component> components;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_product", nullable = false)
    private Product product;

    @Column
    private int price;

}
