package com.dano.kjm.domain.item.entity;

import com.dano.kjm.domain.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public void setItem(Item item) {
        this.item = item;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static CategoryItem create(Item item, Category category) {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.item = item;
        categoryItem.category =category;
        return categoryItem;
    }
}
