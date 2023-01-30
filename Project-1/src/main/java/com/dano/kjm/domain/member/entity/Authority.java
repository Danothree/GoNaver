package com.dano.kjm.domain.member.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public class Authority implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Authority setRoleAndMember(Role role, Member member) {
        Authority authority = new Authority();
        authority.role = role;
        member.addAuthorityList(authority);
        authority.member = member;

        return authority;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public Member getMember() {
        return member;
    }
}
