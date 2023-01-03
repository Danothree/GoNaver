package com.dano.kjm.entity.cart;

import com.dano.kjm.entity.Member;
import com.dano.kjm.entity.basic.BaseEntity;
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
