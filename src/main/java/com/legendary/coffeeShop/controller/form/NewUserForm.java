package com.legendary.coffeeShop.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUserForm {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

}
