package com.dano.kjm.domain.item.dto.request;

import com.dano.kjm.domain.item.entity.ItemStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ItemAddDto {

    @NotBlank(message = "잘못된 요청입니다.")
    private String email;

    @NotBlank(message = "상품 이름을 입력하세요.")
    private String name;

    @NotBlank(message = "가격을 입력하세요.")
    private int price;

    @NotBlank(message = "판매할 수량을 입력하세요.")
    @Size(min = 1, max = 9999, message = "1~9999아래로 입력하세요")
    private int stockQuantity;

    private ItemStatus itemStatus;

    @Size(min = 10, max = 9999, message = "제대로 입력하세요.")
    private String itemDetail;

    private ItemImgDto itemImgDto;
}
