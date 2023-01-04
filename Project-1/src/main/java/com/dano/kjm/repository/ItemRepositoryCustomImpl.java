package com.dano.kjm.repository;

import com.dano.kjm.constant.ItemStatus;
import com.dano.kjm.dto.response.ItemSearchResponse;
import com.dano.kjm.dto.response.PublicItemDto;
import com.dano.kjm.entity.item.Item;
import com.querydsl.core.types.dsl.BooleanExpression;
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
