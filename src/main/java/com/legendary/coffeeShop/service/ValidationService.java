package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.controller.form.UserForm;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.InputMismatchException;

@Service
public class ValidationService {

    /*********************************
     * Public Functions
     *********************************/
    public void validateUserForm(UserForm userForm) {
        if (isUserInvalid(userForm)) {
            throw new InputMismatchException("Username and password are missing or invalid.");
        }
    }

    public void validateProductForm(ProductForm productForm) {
        if (isProductInvalid(productForm)) {
            throw new InputMismatchException("Some product details are missing or invalid.");
        }
    }

    /*********************************
     * Private Functions
     *********************************/
    private boolean isUserInvalid(UserForm userForm) {
        return isEmptyStringIncluded(userForm.getUsername(), userForm.getPassword())
                || isContainsWhitespace(userForm.getUsername(), userForm.getPassword())
                || isContainsNotAllowedCharacters(userForm.getUsername());
    }

    private boolean isProductInvalid(ProductForm productForm) {
        return isEmptyStringIncluded(productForm.getProductType(), productForm.getDisplayName(), productForm.getDescription())
                || isContainsWhitespace(productForm.getProductType())
                || isContainsNotAllowedCharacters(productForm.getProductType());

    }


    private boolean isEmptyStringIncluded(String... strings) {
        for (String string : strings) {
            if (StringUtils.isEmpty(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainsWhitespace(String... strings) {
        for (String string : strings) {
            if (StringUtils.containsWhitespace(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainsNotAllowedCharacters(String... strings) {
        for (String string : strings) {
            if (!string.matches("\\w+")) {
                return true;
            }
        }
        return false;
    }

}
