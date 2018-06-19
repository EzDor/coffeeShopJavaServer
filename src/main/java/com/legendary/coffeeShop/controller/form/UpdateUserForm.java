package com.legendary.coffeeShop.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserForm {

    private String usernameToUpdate;

    private String password;

    private NewUserForm updatedUserDetails;


}
