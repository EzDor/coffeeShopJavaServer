package com.legendary.coffeeShop.controller.form.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatedUserForm {

    private String usernameToUpdate;

    private String password;

    private UserForm updatedUserDetails;


}
