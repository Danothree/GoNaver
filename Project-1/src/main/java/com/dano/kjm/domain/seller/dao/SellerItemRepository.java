package com.dano.kjm.domain.seller.dao;

import com.dano.kjm.domain.seller.entity.SellerItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerItemRepository extends JpaRepository<SellerItem, Long> {

    Page<SellerItem> findBySellerId(Long sellerId, Pageable pageable);
}
