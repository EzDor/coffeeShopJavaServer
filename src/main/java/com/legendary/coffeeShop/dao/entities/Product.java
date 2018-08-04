package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Column(name = "product_type")
    @NotNull
    private ProductType productType;

    @Column
    private double price;

    @Column
    private ProductStatus status;

}
