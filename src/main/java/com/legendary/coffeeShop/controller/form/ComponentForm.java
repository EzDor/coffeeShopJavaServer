package com.legendary.coffeeShop.controller.form;

import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComponentForm {

    private int amount;

    private String name;

    private String type;

    private double price;

    private String image;

    private ComponentStatus status;
}
