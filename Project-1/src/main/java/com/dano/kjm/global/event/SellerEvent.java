package com.dano.kjm.global.event;

import lombok.Getter;

@Getter
public class SellerEvent {

    private String name;

    public SellerEvent(String name) {
        this.name = name;
    }
}
