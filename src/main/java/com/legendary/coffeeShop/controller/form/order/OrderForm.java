package com.legendary.coffeeShop.controller.form.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderForm {

    private List<String> componentsTypes;

    private String productType;
}
