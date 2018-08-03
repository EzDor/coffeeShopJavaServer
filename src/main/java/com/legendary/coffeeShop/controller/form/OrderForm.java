package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderForm {

    private String productName;

    private List<String> componentsNames;

    private int price;
}
