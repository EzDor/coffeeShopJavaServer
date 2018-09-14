package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatedComponentForm {
    private String componentTypeToUpdate;
    private ComponentForm updatedComponentDetails;
}
