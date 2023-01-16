package com.dano.kjm.domain.item.dao;

import com.dano.kjm.domain.item.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemIdAndImgYn(Long itemId, String imgYn);

}
