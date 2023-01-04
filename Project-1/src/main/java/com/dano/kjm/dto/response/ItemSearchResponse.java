package com.dano.kjm.dto.response;

import com.dano.kjm.constant.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchResponse {

    private String searchDate;
    private ItemStatus itemStatus;
    private String searchBy;
    private String searchQuery = "";

}
