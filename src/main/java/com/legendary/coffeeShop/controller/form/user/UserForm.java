package com.legendary.coffeeShop.controller.form.user;

import com.legendary.coffeeShop.dao.entities.user.UserStatus;
import lombok.Data;
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
