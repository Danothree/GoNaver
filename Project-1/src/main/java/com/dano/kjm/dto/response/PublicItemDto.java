package com.dano.kjm.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicItemDto {

    private Long id;

    private String itemName;

    private String itemDetail;

    private String imgUrl;

    private String price;

    public PublicItemDto(Long id, String itemName, String itemDetail, String imgUrl, String price) {
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
