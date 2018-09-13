package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NewComponentForm {

    private int amount;

    private String name;

    private String type;

    private double price;

    private String image;

    private String status;
}
