package com.dano.kjm.repository;

import com.dano.kjm.entity.Authority;
import com.dano.kjm.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByMember(Member member);
}
