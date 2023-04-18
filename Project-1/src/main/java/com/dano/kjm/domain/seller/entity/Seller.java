package com.dano.kjm.domain.seller.entity;

import com.dano.kjm.domain.member.entity.Role;
import com.dano.kjm.domain.member.entity.Authority;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.common.entity.BaseTimeEntity;
import com.dano.kjm.global.error.exception.SellerNotVerifiedException;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Seller extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    private int money;

    @OneToMany(mappedBy = "seller")
    private List<SellerItem> sellerItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ColumnDefault("0")
    private boolean allowSale;

    public static Seller signUpSeller(Member member) {
        if(member.isSeller()) {
            throw new IllegalArgumentException("이미 판매자인 아이디입니다.");
        }
        member.addAuthorityList(Authority.setRoleAndMember(Role.SELLER, member));
        Seller seller = new Seller();
        seller.setMember(member);

        return seller;
    }

    private void setMember(Member member) {
        this.member = member;
    }

    private void verifySellerAuthentication() {
        if(!allowSale){
            throw new SellerNotVerifiedException("판매자 인증이 되지 않은 회원입니다.");
        }
    }
}
