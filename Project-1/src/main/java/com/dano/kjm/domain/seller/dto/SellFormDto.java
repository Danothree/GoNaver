package com.dano.kjm.domain.seller.dto;

import com.dano.kjm.domain.item.entity.ItemStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class SellFormDto {

    private Long id;

    private String name;

    private Integer price;

    private String itemDetail;

    private Integer stockQuantity;

    private ItemStatus itemStatus;

    private String imgUrl;

    @QueryProjection
    public SellFormDto(Long id, String name, Integer price, String itemDetail, Integer stockQuantity, ItemStatus itemStatus, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockQuantity = stockQuantity;
        this.itemStatus = itemStatus;
        this.imgUrl = imgUrl;
    }
}
