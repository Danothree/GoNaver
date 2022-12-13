package com.dano.kjm.service;

import com.dano.kjm.dto.request.MemberUpdateDto;
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

// 전체적으로 들어오는 매개변수의 유효성 검사 필요

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
        authorityRepository.saveAll(member.getAuthorities());
    }

    // 1) memberRepository.findByEmail 값이 null일 때, null 값만 가져오고 이에 대한 처리가 없음(유효성 검사)
    // 2) 매개변수 email에 대한 유효성 검사 필요
    // 3) MemberDetail.createMemberFormDto 이 메서드명이 의미가 명확하지 않음. MemberFormDto를 만든다는 뜻인지 모룸
    public MemberDetail findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        MemberDetail memberDetail = MemberDetail.createMemberFormDto(member);
        return memberDetail;
    }


    // 1) 만약 findByEmail을 통해 null값이 반환된다면 member.updateMember() 시 NullPointException 발생
    @Transactional
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findByEmail(memberUpdateDto.getEmail()).orElse(null);
        member.updateMember(memberUpdateDto, passwordEncoder);
    }

    // 1) 만약 null 값이라면 null을 지우게 되고 확실하지 않으나 NullPointException이 나올 수 있고
    // null로 실패했을때, 로그나 예외처리를 통해 Client 또는 사용자에게 예외 상황을 알릴 필요가 있음
    @Transactional
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        memberRepository.delete(member);
    }

    // 잘하면 한줄로 표현 가능
    public void duplicatedCheck(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        if(member != null) {
            throw new DuplicatedException("이미 존재하는 회원입니다.");
        }
    }
}
