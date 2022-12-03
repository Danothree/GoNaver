package com.dano.kjm.config;

import com.dano.kjm.entity.Member;
import com.dano.kjm.exception.UserNotFoundException;
import com.dano.kjm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(member.getEmail(), member.getPassword(), null);
    }
}
