package com.dano.kjm.service;

import com.dano.kjm.dto.request.MemberUpdateDto;
import com.dano.kjm.dto.request.SignUpDto;
import com.dano.kjm.dto.response.MemberDetail;
import com.dano.kjm.entity.Member;
import com.dano.kjm.exception.BadParameterException;
import com.dano.kjm.exception.DuplicatedException;
import com.dano.kjm.exception.UserNotFoundException;
import com.dano.kjm.repository.AuthorityRepository;
import com.dano.kjm.repository.MemberRepository;
import com.dano.kjm.util.CustomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        authorityRepository.saveAll(member.getAuthorities());
    }

    public MemberDetail findMember(String email) {
        Assert.notNull(email,"입력된 정보가 없습니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        MemberDetail memberDetail = MemberDetail.createResponseDto(member);
        return memberDetail;
    }

    @Transactional
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        Assert.notNull(memberUpdateDto,"입력된 정보가 없습니다.");
        Member member = memberRepository.findByEmail(memberUpdateDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        member.updateMember(memberUpdateDto, passwordEncoder);
    }

    @Transactional
    public void deleteMember(String email) {
        Assert.notNull(email,"입력된 정보가 없습니다.");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        memberRepository.delete(member);
    }

    // 잘하면 한줄로 표현 가능
    public void duplicatedCheck(String email) {
        Assert.notNull(email,"입력된 정보가 없습니다.");
        if(memberRepository.existsByEmail(email)) {
            throw new DuplicatedException("중복된 이메일입니다.");
        };
    }

    public void objCheck(Object object) {
        if(object == null) {
            throw new BadParameterException("잘못된 요청입니다.");
        }
    }

    public void stringCheck(String str) {
        if(CustomUtil.isEmpty(str)) {
            throw new BadParameterException("잘못된 요청입니다.");
        }
    }
}
