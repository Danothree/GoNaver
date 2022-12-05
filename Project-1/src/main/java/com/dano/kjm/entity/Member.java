package com.dano.kjm.entity;

import com.dano.kjm.constant.Role;
import com.dano.kjm.dto.MemberFormDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Authority> authorityList = new ArrayList<>();

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder encoder) {
        Member member = new Member();
        member.setEmail(memberFormDto.getEmail());
        String password = encoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setPhone(memberFormDto.getPhone());
        member.setAddress(new Address(memberFormDto.getCity(), memberFormDto.getStreet(), memberFormDto.getPostalCode()));
        Authority authority = Authority.setRoleAndMember(Role.CONSUMER, member);
        member.addAuthorityList(authority);
        return member;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public void addAuthorityList(Authority authority) {
        this.authorityList.add(authority);
    }

}
