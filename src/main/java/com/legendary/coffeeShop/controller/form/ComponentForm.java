package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ComponentForm {

    private int amount;

    private String name;

    private double price;

    private List<String> productDisplayName;
}
