package com.legendary.coffeeShop.dao.entities.order;

public enum OrderStatus {
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CANCELED("CANCELED");


    private final String text;

    OrderStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
