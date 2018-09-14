package com.legendary.coffeeShop.dao.entities;

public enum ProductStatus {
    ACTIVE("ACTIVE"),
    DISCARDED("DISCARDED");



    private final String text;

    ProductStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
