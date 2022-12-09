package com.dano.kjm.entity;

import com.dano.kjm.constant.Role;
import com.dano.kjm.dto.request.MemberUpdateDto;
import com.dano.kjm.dto.request.SignUpDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String email;
    private String password;
    private String phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    public static Member createMember(SignUpDto signUpDto, PasswordEncoder encoder) {
        Member member = new Member();
        member.setEmail(signUpDto.getEmail());
        String password = encoder.encode(signUpDto.getPassword());
        member.setUsername(signUpDto.getUsername());
        member.setPassword(password);
        member.setPhone(signUpDto.getPhone());
        member.setAddress(
                Address.create(signUpDto.getAddress(), signUpDto.getDetailAddress())
        );
        Authority authority = Authority.setRoleAndMember(Role.CONSUMER, member);
        member.addAuthorityList(authority);
        return member;
    }

    public void updateMember(MemberUpdateDto memberUpdateDto, PasswordEncoder encoder) {
        String password = encoder.encode(memberUpdateDto.getPassword());
        this.username = memberUpdateDto.getUsername();
        this.password = password;
        this.address = Address.create(memberUpdateDto.getAddress(), memberUpdateDto.getDetailAddress());
        this.phone = memberUpdateDto.getPhone();
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
        this.authorities.add(authority);
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
