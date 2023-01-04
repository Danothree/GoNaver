package com.dano.kjm.dto.request;

import com.dano.kjm.constant.ItemStatus;
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
