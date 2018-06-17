package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductForm {

    private String displayName;

    private String productType;

    private String description;

    private double price;

    private String productTypeToUpdate;
}
