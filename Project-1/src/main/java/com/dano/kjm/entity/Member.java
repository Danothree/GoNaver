package com.dano.kjm.entity;

import com.dano.kjm.constant.Role;
import com.dano.kjm.dto.MemberDto;
import com.dano.kjm.dto.MemberFormDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder encoder) {
        Member member = new Member();
        member.setEmail(memberFormDto.getEmail());
        String password = encoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setPhone(memberFormDto.getPhone());
        member.setAddress(memberFormDto.getAddress());
        member.setRole(memberFormDto.getRole());
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

    private void setRole(Role role) {
        this.role = role;
    }
}
