package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.dto.response.ItemSearchResponse;
import com.dano.kjm.domain.item.dto.response.PublicItemDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

//    private BooleanExpression searchSellStatus(ItemStatus itemStatus) {
////        return itemStatus == null ? null : QItem.item.itemStatus.eq(itemStatus);
//    }

    @Override
    public Page<Item> getSellerItemPage(ItemSearchResponse itemSearch, Pageable pageable) {
        return null;
    }

    @Override
    public Page<PublicItemDto> getPublicItemPage(ItemSearchResponse itemSearch, Pageable pageable) {
        return null;
    }
}
