package com.dano.kjm.domain.seller.dao;

import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByMember(Member member);
}
