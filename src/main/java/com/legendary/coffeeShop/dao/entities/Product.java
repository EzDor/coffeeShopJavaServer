package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "display_name", unique = true)
    private String displayName;

    @Column
    private String description;

    @Column(name = "product_type", unique = true)
    @NotBlank
    private String productType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_components", joinColumns = {@JoinColumn(name = "component_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    Set<Component> productComponents = new HashSet<>();

    @Column
    private double price;

    @Column
    private ProductStatus status;

    @Column
    private String image;

}
