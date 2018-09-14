package com.legendary.coffeeShop.controller.form;

import com.legendary.coffeeShop.dao.entities.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {

    private String firstName;

    private String lastName;

    private String username;

    private UserStatus status;

    private boolean admin;

    private String password;

}
