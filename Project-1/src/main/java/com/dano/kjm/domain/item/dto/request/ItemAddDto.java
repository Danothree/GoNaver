package com.dano.kjm.domain.item.dto.request;

import com.dano.kjm.domain.item.entity.ItemStatus;
import lombok.Data;

@Data
public class ItemAddDto {

    private String name;
    private int price;
    private int stockQuantity;
    private ItemStatus itemStatus;
    private String itemDetail;
}
