package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.controller.form.OrderForm;
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

    public void validateComponentForm(ComponentForm componentForm) {
        if (isComponentInvalid(componentForm)) {
            throw new InputMismatchException("Some component details are missing or invalid.");
        }
    }


    public void validateOrderForm(OrderForm orderForm) {
        if (isOrderInvalid(orderForm)) {
            throw new InputMismatchException("Some component details are missing or invalid.");
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
        return isEmptyStringIncluded(productForm.getType(), productForm.getName(), productForm.getDescription())
                || isContainsNotAllowedCharacters(productForm.getType())
                || isContainsWhitespace(productForm.getType());

    }

    private boolean isComponentInvalid(ComponentForm componentForm) {
        return isEmptyStringIncluded(componentForm.getType(), componentForm.getName())
                || isContainsWhitespace(componentForm.getType())
                || isContainsNotAllowedCharacters(componentForm.getType())
                || componentForm.getStatus() == null
                || componentForm.getAmount() < 0
                || componentForm.getPrice() < 0;
    }

    private boolean isOrderInvalid(OrderForm orderForm) {
        return isEmptyStringIncluded(orderForm.getProductName())
                || isEmptyStringIncluded(orderForm.getComponentsNames().toArray(new String[0]))
                || orderForm.getPrice() < 0
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
