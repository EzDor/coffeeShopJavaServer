package com.legendary.coffeeShop.controller.form;

import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComponentForm {

    private int id;

    private int amount;

    private String name;

    private double price;

    private ComponentStatus status;
}
