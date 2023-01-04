package com.dano.kjm.repository;

import com.dano.kjm.dto.response.ItemSearchResponse;
import com.dano.kjm.dto.response.PublicItemDto;
import com.dano.kjm.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getSellerItemPage(ItemSearchResponse itemSearch, Pageable pageable);

    Page<PublicItemDto> getPublicItemPage(ItemSearchResponse itemSearch, Pageable pageable);
}
