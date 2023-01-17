package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
