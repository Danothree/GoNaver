package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.CategoryItemRepository;
import com.dano.kjm.domain.item.dao.CategoryRepository;
import com.dano.kjm.domain.item.dao.ItemImgRepository;
import com.dano.kjm.domain.item.dao.ItemRepository;
import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.*;
import com.dano.kjm.domain.seller.application.SellerItemService;
import com.dano.kjm.domain.seller.application.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;
    private final SellerItemService sellerItemService;
    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public void saveItem(ItemAddDto itemAddDto, MultipartFile itemImgFile) throws Exception {
        Item item = Item.createItem(itemAddDto);
        Category category = new Category().createCategory(ItemType.valueOf(itemAddDto.getItemType()));
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(category);
        categoryItem.setItem(item);
        categoryRepository.save(category);
        categoryItemRepository.save(categoryItem);
        itemRepository.save(item);
        sellerItemService.save(itemAddDto.getMemberId(), item);
        for (int i = 0; i < 1; i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.itemCreate(item);
            if(i == 0) {
                itemImg.setImgYn("Y");
            } else {
                itemImg.setImgYn("N");
            }
            itemImgService.saveImg(itemImg, itemImgFile);
        }
    }
}
