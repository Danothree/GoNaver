package com.dano.kjm.domain.item.entity;

public enum ItemType {
    TOP("모자"), PANTS("바지"), OUTER("상의"), ACCESSORY("악세사리");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
