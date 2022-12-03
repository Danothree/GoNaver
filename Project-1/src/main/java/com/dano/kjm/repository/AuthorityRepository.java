package com.dano.kjm.repository;

import com.dano.kjm.entities.Authority;
import com.dano.kjm.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByMember(Member member);
}
