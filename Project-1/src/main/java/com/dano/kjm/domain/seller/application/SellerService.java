package com.dano.kjm.domain.seller.application;

import com.dano.kjm.domain.item.application.ItemImgService;
import com.dano.kjm.domain.item.dao.ItemImgRepository;
import com.dano.kjm.domain.item.dao.ItemRepository;
import com.dano.kjm.domain.item.dto.request.ItemImgDto;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.item.entity.ItemImg;
import com.dano.kjm.domain.member.dao.MemberRepository;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.member.exception.UserNotFoundException;
import com.dano.kjm.domain.seller.dao.SellerItemRepository;
import com.dano.kjm.domain.seller.dao.SellerRepository;
import com.dano.kjm.domain.seller.dto.SellFormDto;
import com.dano.kjm.domain.seller.entity.Seller;
import com.dano.kjm.domain.seller.entity.SellerItem;
import com.dano.kjm.global.event.SellerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ApplicationEventPublisher publisher;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Seller findByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return sellerRepository.findByMember(member);
    }

    @Transactional
    public void savedSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Seller seller = Seller.signUpSeller(member);
        sellerRepository.save(seller);
        publisher.publishEvent(new SellerEvent(seller.getMember().getUsername()));
    }

    @Transactional
    public void deleteSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        sellerRepository.deleteById(member.getId());
    }

    public Page<SellFormDto> getItems(Pageable pageable) {
        return itemRepository.getPublicItemPage(pageable);
    }

}
