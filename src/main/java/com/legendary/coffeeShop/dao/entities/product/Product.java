package com.legendary.coffeeShop.dao.entities.product;

import com.legendary.coffeeShop.dao.entities.component.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(unique = true)
    @NotBlank
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_components")
    Set<Component> productComponents = new HashSet<>();

    @Column
    private double price;

    @Column
    private ProductStatus status;

    @Column
    private String image;

}
