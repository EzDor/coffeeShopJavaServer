package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.controller.form.OrderForm;
import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.dao.entities.ProductType;
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

    public void validateUpdateProductForm(ProductForm productForm) {
        if (isUpdateProductInvalid(productForm)) {
            throw new InputMismatchException("Some product details are missing or invalid.");
        }
    }
    public void validateComponentForm(ComponentForm componentForm) {
        if (isComponentInvalid(componentForm)) {
            throw new InputMismatchException("Some component details are missing or invalid.");
        }
    }

    public void validateProductType(String productType) {
        if (isProductTypeNotExists(productType)) {
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
    private boolean isUserInvalid(NewUserForm newUserForm) {
        return isEmptyStringIncluded(newUserForm.getUsername(), newUserForm.getPassword())
                || isContainsWhitespace(newUserForm.getUsername(), newUserForm.getPassword())
                || isContainsNotAllowedCharacters(newUserForm.getUsername());
    }

    private boolean isProductInvalid(ProductForm productForm) {
        return isEmptyStringIncluded(productForm.getProductType(), productForm.getDisplayName())
                || isProductTypeNotExists(productForm.getProductType());

    }

    private boolean isUpdateProductInvalid(ProductForm productForm) {
        return StringUtils.isEmpty(productForm.getDisplayName());
    }

    private boolean isComponentInvalid(ComponentForm componentForm) {
        return StringUtils.isEmpty(componentForm.getName())
                || isEmptyStringIncluded((String[])componentForm.getProductDisplayName().toArray())
                || componentForm.getAmount() < 0
                || componentForm.getPrice() < 0
                ;
    }

    private boolean isOrderInvalid(OrderForm orderForm) {
        return isEmptyStringIncluded(orderForm.getProductName())
                || isEmptyStringIncluded((String[])orderForm.getComponentsNames().toArray())
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

    private boolean isProductTypeNotExists(String productType) {
        for (ProductType pType : ProductType.values()) {
            if (pType.name().equals(productType.toUpperCase())) {
                return false;
            }
        }
        return true;
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
