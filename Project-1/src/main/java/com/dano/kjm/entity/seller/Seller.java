package com.dano.kjm.entity.seller;

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
        Seller seller = new Seller();
        seller.setMember(member);

        return seller;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
