package com.dano.kjm.domain.item.dto.request;

import com.dano.kjm.domain.item.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String imgYn;

    public ItemImgDto createImgDto(ItemImg itemImg) {
        this.id = itemImg.getId();
        this.imgName = itemImg.getImgName();
        this.oriImgName = itemImg.getOriImgName();
        this.imgUrl = itemImg.getImgUrl();
        this.imgYn = itemImg.getImgYn();

        return this;
    }
}
