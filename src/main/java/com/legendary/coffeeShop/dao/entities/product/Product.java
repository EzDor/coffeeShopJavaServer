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
    @JoinTable(name = "product_components")
    Set<Component> productComponents = new HashSet<>();

    @Column
    private double price;

    @Column
    private ProductStatus status;

    @Column
    private String image;

}
