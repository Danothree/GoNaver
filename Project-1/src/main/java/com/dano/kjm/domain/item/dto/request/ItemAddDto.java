package com.dano.kjm.domain.item.dto.request;

import com.dano.kjm.domain.item.entity.ItemStatus;
import com.dano.kjm.domain.item.entity.ItemType;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemAddDto {

    private Long memberId;

    @NotBlank(message = "상품 이름을 입력하세요.")
    private String name;

    @NotNull(message = "가격을 입력하세요.")
    private int price;

    @NotNull(message = "판매할 수량을 입력하세요.")
    @Min(value = 1, message = "한개 이상만 가능합니다.")
    @Max(value = 9999, message = "9999개 아래로만 가능합니다.")
    private int stockQuantity;

    private ItemStatus itemStatus;

    @NotBlank(message = "타입을 선택해주세요.")
    private String itemType;

    @Size(min = 10, max = 9999, message = "제대로 입력하세요.")
    private String itemDetail;

    private List<ItemImgDto> itemImgDto = new ArrayList<>();

}
