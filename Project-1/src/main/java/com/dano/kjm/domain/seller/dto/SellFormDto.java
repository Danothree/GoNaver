package com.dano.kjm.domain.seller.dto;

import com.dano.kjm.domain.item.dto.request.ItemImgDto;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.entity.ItemStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SellFormDto {

    private Long id;

    private String name;

    private Integer price;

    private String itemDetail;

    private Integer stockQuantity;

    private ItemStatus itemStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    public SellFormDto createForm(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.itemDetail = item.getItemDetail();
        this.stockQuantity = item.getStockQuantity();
        this.itemStatus = item.getItemStatus();
        return this;
    }
}
