package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank
    private String productType;

    @Column
    private double price;

    @Column
    private ProductStatus status;

}
