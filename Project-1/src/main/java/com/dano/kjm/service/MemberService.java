package com.dano.kjm.service;

import com.dano.kjm.dto.request.SignUpDto;
import com.dano.kjm.dto.response.MemberDetail;
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
    public void saveMember(SignUpDto signUpDTO) {
        duplicatedCheck(signUpDTO.getEmail());
        Member member = Member.createMember(signUpDTO, passwordEncoder);
        memberRepository.save(member);
        authorityRepository.saveAll(member.getAuthorityList());
    }

    public SignUpDto findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        SignUpDto memberFormDto = new SignUpDto();
        MemberDetail.createMemberFormDto(member);
        return memberFormDto;
    }

    @Transactional
    public void updateMember(MemberDetail memberDetail) {
        Member member = memberRepository.findByEmail(memberDetail.getEmail()).orElse(null);
        member.updateMember(memberDetail, passwordEncoder);
    }

    @Transactional
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        memberRepository.delete(member);
    }

    public void duplicatedCheck(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        if(member != null) {
            throw new DuplicatedException("이미 존재하는 회원입니다.");
        }
    }
}
