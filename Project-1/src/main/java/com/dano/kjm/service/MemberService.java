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
        authorityRepository.saveAll(member.getAuthorityList());
    }

    public void DuplicatedCheck(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);

        if(member != null) {
            throw new DuplicatedException("이미 존재하는 회원입니다.");
        }
    }

    public MemberFormDto findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.createMemberFormDto(member);
        return memberFormDto;
    }

    @Transactional
    public void updateMember(MemberFormDto memberFormDto) {
        Member member = memberRepository.findByEmail(memberFormDto.getEmail()).orElse(null);
        member.updateMember(memberFormDto, passwordEncoder);
    }

    @Transactional
    public void deleteMember(String email) {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
//            throw new UserNotFoundException("존재하지 않는 이메일입니다");
//        });
//        memberRepository.delete(member);
    }
}
