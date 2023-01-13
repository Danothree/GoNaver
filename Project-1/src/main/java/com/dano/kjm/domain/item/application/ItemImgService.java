package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemImgService {

    private final ItemImgRepository itemImgRepository;
}
