package com.dano.kjm.domain.cart.entity;

import com.dano.kjm.domain.common.entity.BaseEntity;
import com.dano.kjm.domain.item.entity.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CartItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}
