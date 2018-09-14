package com.legendary.coffeeShop.dao.entities;

public enum ComponentStatus {
    ACTIVE("ACTIVE"),
    OUT_OF_STOCK("OUT_OF_STOCK"),
    DISCARDED("DISCARDED");




    private final String text;

    ComponentStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}


