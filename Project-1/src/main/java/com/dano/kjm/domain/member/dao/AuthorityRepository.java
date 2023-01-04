package com.dano.kjm.domain.member.dao;

import com.dano.kjm.domain.member.entity.Authority;
import com.dano.kjm.domain.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByMember(Member member);
}
