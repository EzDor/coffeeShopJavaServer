package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_types", joinColumns = {@JoinColumn(name = "component_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> productTypes = new HashSet<>();

    @Column
    private ComponentStatus status;

    @Column
    private int amount;
    

}
