package com.dano.kjm.repository;

import com.dano.kjm.entity.Authority;
import com.dano.kjm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> findByMember(Member member);
}
