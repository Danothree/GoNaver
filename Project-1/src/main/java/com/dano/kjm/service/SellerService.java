package com.dano.kjm.service;

import com.dano.kjm.entity.Member;
import com.dano.kjm.entity.seller.Seller;
import com.dano.kjm.exception.UserNotFoundException;
import com.dano.kjm.repository.MemberRepository;
import com.dano.kjm.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final MemberRepository memberRepository;

    public void savedSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Seller seller = Seller.signUpSeller(member);
        sellerRepository.save(seller);
    }

    public void deleteSeller(String email) {
        Assert.notNull(email, "잘못된 요청입니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        sellerRepository.deleteById(member.getId());
    }
}
