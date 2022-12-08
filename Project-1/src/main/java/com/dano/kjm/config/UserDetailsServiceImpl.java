package com.dano.kjm.config;

import com.dano.kjm.config.security.SecurityMember;
import com.dano.kjm.constant.Role;
import com.dano.kjm.entity.Authority;
import com.dano.kjm.entity.Member;
import com.dano.kjm.exception.UserNotFoundException;
import com.dano.kjm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        return new SecurityMember(member, member.getAuthorities().stream()
                .map(Authority::getRole)
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
