package com.dano.kjm.domain.cart.entity;

import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cart extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
