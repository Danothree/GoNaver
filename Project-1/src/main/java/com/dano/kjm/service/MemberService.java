package com.dano.kjm.service;

import com.dano.kjm.constant.Role;
import com.dano.kjm.dto.MemberDto;
import com.dano.kjm.dto.MemberFormDto;
import com.dano.kjm.entity.Authority;
import com.dano.kjm.entity.Member;
import com.dano.kjm.exception.DuplicatedException;
import com.dano.kjm.exception.UserNotFoundException;
import com.dano.kjm.repository.AuthorityRepository;
import com.dano.kjm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberFormDto memberFormDto) {
        DuplicatedCheck(memberFormDto.getEmail());
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberRepository.save(member);
        if("SELLER".equals(memberFormDto.getRole())) {
            authorityRepository.save(Authority.setRoleAndMember(Role.SELLER, member));
        } else {
            authorityRepository.save(Authority.setRoleAndMember(Role.CONSUMER, member));
        }

    }

    public void DuplicatedCheck(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);

        if(member != null) {
            throw new DuplicatedException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void updateMember(MemberDto memberDto) {

    }

    @Transactional
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException("존재하지 않는 이메일입니다");
        });
        memberRepository.delete(member);
    }
}
