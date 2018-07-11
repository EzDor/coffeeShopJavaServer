package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.controller.form.NewUserForm;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.InputMismatchException;

@Service
public class ValidationService {

    /*********************************
     * Public Functions
     *********************************/
    public void validateUserForm(NewUserForm newUserForm) {
        if (isUserInvalid(newUserForm)) {
            throw new InputMismatchException("Username and password are missing or invalid.");
        }
    }

    public void validateProductForm(ProductForm productForm) {
        if (isProductInvalid(productForm)) {
            throw new InputMismatchException("Some product details are missing or invalid.");
        }
    }

    public void validateComponentForm(ComponentForm componentForm) {
        if (isComponentValid(componentForm)) {
            throw new InputMismatchException("Some component details are missing or invalid.");
        }
    }


    /*********************************
     * Private Functions
     *********************************/
    private boolean isUserInvalid(NewUserForm newUserForm) {
        return isEmptyStringIncluded(newUserForm.getUsername(), newUserForm.getPassword())
                || isContainsWhitespace(newUserForm.getUsername(), newUserForm.getPassword())
                || isContainsNotAllowedCharacters(newUserForm.getUsername());
    }

    private boolean isProductInvalid(ProductForm productForm) {
        return isEmptyStringIncluded(productForm.getProductType(), productForm.getDisplayName(), productForm.getDescription())
                || isContainsWhitespace(productForm.getProductType())
                || isContainsNotAllowedCharacters(productForm.getProductType());

    }

    private boolean isComponentValid(ComponentForm componentForm) {
        return isEmptyStringIncluded(componentForm.getName())
                // @TODO : Add more validation
                ;
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
