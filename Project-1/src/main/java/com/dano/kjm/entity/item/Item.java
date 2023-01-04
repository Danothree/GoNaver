package com.dano.kjm.entity.item;

import com.dano.kjm.constant.ItemStatus;
import com.dano.kjm.dto.request.ItemUpdateDto;
import com.dano.kjm.entity.basic.BaseTimeEntity;
import com.dano.kjm.entity.order.OrderItem;
import com.dano.kjm.exception.BadParameterException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Item extends BaseTimeEntity {

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
