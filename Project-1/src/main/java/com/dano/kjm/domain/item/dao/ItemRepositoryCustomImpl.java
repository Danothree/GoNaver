package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.dto.response.ItemSearchResponse;
import com.dano.kjm.domain.item.entity.ItemStatus;
import com.dano.kjm.domain.item.entity.QItem;
import com.dano.kjm.domain.item.entity.QItemImg;
import com.dano.kjm.domain.seller.dto.QSellFormDto;
import com.dano.kjm.domain.seller.dto.SellFormDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public Page<SellFormDto> getPublicItemPage(Pageable pageable) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        List<SellFormDto> sellFormDtos = queryFactory
                .select(
                        new QSellFormDto(
                                item.id,
                                item.name,
                                item.price,
                                item.itemDetail,
                                item.stockQuantity,
                                item.itemStatus,
                                itemImg.imgUrl
                        )

                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.imgYn.eq("Y"))
                .orderBy(item.stockQuantity.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count).from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.imgYn.eq("Y"))
                .fetchOne();
        return new PageImpl<>(sellFormDtos, pageable, total);
    }
}


