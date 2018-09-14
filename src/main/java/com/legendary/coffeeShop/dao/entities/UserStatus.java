package com.legendary.coffeeShop.dao.entities;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    DISCARDED("DISCARDED");



    private final String text;

    UserStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
