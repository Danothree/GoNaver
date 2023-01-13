package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.ItemRepository;
import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(ItemAddDto itemAddDto, MultipartFile itemImgFile) {
        Item item = Item.createItem(itemAddDto);
        itemRepository.save(item);
    }
}
