package com.dano.kjm.entities;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Authority {

    @Id @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
