package com.dano.kjm.config.security;

import com.dano.kjm.entities.Authority;
import com.dano.kjm.entities.Member;
import com.dano.kjm.entities.Role;
import com.dano.kjm.repository.AuthorityRepository;
import com.dano.kjm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("username이 없어용"));

        return new SecurityUser(member, member.getAuthorities()
                                            .stream()
                .map(Authority::getRole)
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(toList()));
    }
}
