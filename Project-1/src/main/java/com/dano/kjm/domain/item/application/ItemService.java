package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.ItemImgRepository;
import com.dano.kjm.domain.item.dao.ItemRepository;
import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.entity.ItemImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public void saveItem(ItemAddDto itemAddDto, MultipartFile itemImgFile) throws Exception {
        Item item = Item.createItem(itemAddDto);
        itemRepository.save(item);

//        for (int i = 0; i < itemImgFile.getSize(); i++) {
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
