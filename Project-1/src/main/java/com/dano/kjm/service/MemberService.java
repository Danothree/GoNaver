package com.dano.kjm.service;

import com.dano.kjm.dto.request.MemberFormRqDto;
import com.dano.kjm.entity.Member;
import com.dano.kjm.exception.DuplicatedException;
import com.dano.kjm.repository.AuthorityRepository;
import com.dano.kjm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberFormRqDto memberFormRqDto) {
        DuplicatedCheck(memberFormRqDto.getEmail());
        Member member = Member.createMember(memberFormRqDto, passwordEncoder);
        memberRepository.save(member);
        authorityRepository.saveAll(member.getAuthorityList());
    }

    public void DuplicatedCheck(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);

        if(member != null) {
            throw new DuplicatedException("이미 존재하는 회원입니다.");
        }
    }

    public MemberFormRqDto findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        MemberFormRqDto memberFormDto = new MemberFormRqDto();
        memberFormDto.createMemberFormDto(member);
        return memberFormDto;
    }

    @Transactional
    public void updateMember(MemberFormRqDto memberFormRqDto) {
        Member member = memberRepository.findByEmail(memberFormRqDto.getEmail()).orElse(null);
        member.updateMember(memberFormRqDto, passwordEncoder);
    }

    @Transactional
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        memberRepository.delete(member);
    }
}
