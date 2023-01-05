package com.dano.kjm.domain.seller.application;

import com.dano.kjm.domain.member.dto.response.MemberDetail;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.member.dao.MemberRepository;
import com.dano.kjm.domain.seller.entity.Seller;
import com.dano.kjm.domain.seller.dao.SellerRepository;
import com.dano.kjm.global.event.SellerEvent;
import com.dano.kjm.domain.member.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ApplicationEventPublisher publisher;
    private final MemberRepository memberRepository;

    public Seller findByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return sellerRepository.findByMember(member);
    }
    public void savedSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Seller seller = Seller.signUpSeller(member);
        sellerRepository.save(seller);
        publisher.publishEvent(new SellerEvent(seller.getMember().getUsername()));
    }

    public void deleteSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        sellerRepository.deleteById(member.getId());
    }
}
