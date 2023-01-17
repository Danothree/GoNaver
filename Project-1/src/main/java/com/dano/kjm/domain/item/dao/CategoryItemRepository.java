package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
}
