package com.dano.kjm.entity.item;

import com.dano.kjm.entity.basic.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class CategoryItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
