package com.dano.kjm.domain.item.dto.request;

import com.dano.kjm.domain.item.entity.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateDto {

    private String name;
    private int price;
    private int stockQuantity;
    private ItemStatus itemStatus;
    private String itemDetail;

}
