package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.dto.response.ItemSearchResponse;
import com.dano.kjm.domain.item.dto.response.PublicItemDto;
import com.dano.kjm.domain.seller.dto.SellFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getSellerItemPage(ItemSearchResponse itemSearch, Pageable pageable);

    Page<SellFormDto> getPublicItemPage(Pageable pageable);
}
