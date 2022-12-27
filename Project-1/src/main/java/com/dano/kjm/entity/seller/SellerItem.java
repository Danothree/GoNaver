package com.dano.kjm.entity.seller;

import com.dano.kjm.entity.basic.BaseEntity;
import com.dano.kjm.entity.item.Item;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class SellerItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
