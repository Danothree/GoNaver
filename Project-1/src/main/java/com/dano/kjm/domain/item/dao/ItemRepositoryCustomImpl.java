package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.dto.response.ItemSearchResponse;
import com.dano.kjm.domain.item.dto.response.PublicItemDto;
import com.dano.kjm.domain.item.entity.ItemStatus;
import com.dano.kjm.domain.item.entity.QItem;
import com.dano.kjm.domain.item.entity.QItemImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;


public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatus(ItemStatus itemStatus) {
        return itemStatus == null ? null : QItem.item.itemStatus.eq(itemStatus);
    }

    private BooleanExpression searchBy(String searchBy, String searchQuery) {
        if("itemName".equals(searchBy)) {
            return QItem.item.name.like("%"+searchQuery+"%");
        } else if("createdBy".equals(searchBy)) {
            return QItem.item.createdBy.like("%"+searchQuery+"%");
        }
        return null;
    }

    private BooleanExpression itemNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ?
                null : QItem.item.name.like("%"+ searchQuery +"%");
    }

    @Override
    public Page<Item> getSellerItemPage(ItemSearchResponse itemSearch, Pageable pageable) {
        List<Item> items = queryFactory.selectFrom(QItem.item)
                .where(searchSellStatus(itemSearch.getItemStatus()),
                        searchBy(itemSearch.getSearchBy(), itemSearch.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(Wildcard.count).from(QItem.item)
                .where(searchSellStatus(itemSearch.getItemStatus()),
                        searchBy(itemSearch.getSearchBy(), itemSearch.getSearchQuery()))
                .fetchOne();
        return new PageImpl<>(items, pageable, total);
    }

    @Override
    public Page<PublicItemDto> getPublicItemPage(ItemSearchResponse itemSearch, Pageable pageable) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

//        queryFactory
//                .select(new QItem)
        return null;
    }
}


