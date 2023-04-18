package com.dano.kjm.domain.seller.dao;

import com.dano.kjm.domain.seller.entity.SellerCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * SellerCodeRepository.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.18
 */
public interface SellerCodeRepository extends JpaRepository<SellerCode, Long> {

    Optional<SellerCode> findAllByEmail(String email);
}
