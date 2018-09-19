package com.legendary.coffeeShop.controller.form.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreditCardForm {
    private String creditNumber;
    private String expireDate;
    private String cvv;
}
