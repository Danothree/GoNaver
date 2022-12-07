package com.dano.kjm.entity;

import com.dano.kjm.constant.Role;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Authority {

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
}
