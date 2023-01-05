package com.dano.kjm.domain.item.entity;

import com.dano.kjm.domain.common.entity.BaseEntity;
import com.dano.kjm.domain.item.dto.request.ItemUpdateDto;
import com.dano.kjm.domain.common.entity.BaseTimeEntity;
import com.dano.kjm.domain.order.entity.OrderItem;
import com.dano.kjm.global.error.exception.BadParameterException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private String itemDetail;

    private int price;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    private int stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void updateItem(ItemUpdateDto itemUpdateDto) {
        this.name = itemUpdateDto.getName();
        this.price = itemUpdateDto.getPrice();
        this.stockQuantity = itemUpdateDto.getStockQuantity();
        this.itemStatus = itemUpdateDto.getItemStatus();
        this.itemDetail = itemUpdateDto.getItemDetail();
    }
    public void addCategoryItems(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
        categoryItem.setItem(this);
    }
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int count) {
        int restStock = this.stockQuantity - count;
        if(restStock < 0) {
            throw new BadParameterException("재고가 부족합니다.");
        }
        this.stockQuantity -= count;
    }
}
