package com.dano.kjm.entity.seller;

import com.dano.kjm.constant.Role;
import com.dano.kjm.entity.Authority;
import com.dano.kjm.entity.Member;
import com.dano.kjm.entity.basic.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Seller extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    private int money;

    @OneToMany(mappedBy = "seller")
    private List<SellerItem> sellerItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Seller signUpSeller(Member member) {
        if(member.isSeller()) {
            throw new IllegalArgumentException("이미 판매자인 아이디입니다.");
        }
        member.addAuthorityList(Authority.setRoleAndMember(Role.SELLER, member));
        Seller seller = new Seller();
        seller.setMember(member);

        return seller;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
