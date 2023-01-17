package com.dano.kjm.domain.seller.application;

import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.member.dao.MemberRepository;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.seller.dao.SellerItemRepository;
import com.dano.kjm.domain.seller.dao.SellerRepository;
import com.dano.kjm.domain.seller.entity.Seller;
import com.dano.kjm.domain.seller.entity.SellerItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerItemService {

    private final SellerItemRepository sellerItemRepository;
    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;

    public void save(Long memberId, Item item) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Seller seller = sellerRepository.findByMember(member);
        SellerItem sellerItem = new SellerItem(seller, item);
        sellerItemRepository.save(sellerItem);
    }
}
