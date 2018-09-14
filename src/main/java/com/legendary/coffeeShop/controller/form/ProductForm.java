package com.legendary.coffeeShop.controller.form;

import com.legendary.coffeeShop.dao.entities.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProductForm {

    private String name;

    private String type;

    private String description;

    private double price;

    private ProductStatus status;

    private List<String> componentsTypes;

    private String image;

}
