package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.CategoryItemRepository;
import com.dano.kjm.domain.item.dao.CategoryRepository;
import com.dano.kjm.domain.item.entity.Category;
import com.dano.kjm.domain.item.entity.CategoryItem;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.entity.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    public void save(ItemType itemType, Item item) {
        Category category = Category.create(itemType);
        CategoryItem categoryItem = CategoryItem.create(item, category);
        categoryRepository.save(category);
        categoryItemRepository.save(categoryItem);
    }
}
