package com.dano.kjm.domain.seller.entity;

import com.dano.kjm.domain.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * SellerCode.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.18
 */
@Getter
@Entity
@Table(name = "seller_auth_code")
@NoArgsConstructor
public class SellerCode extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String authCode;

    @Column(nullable = false)
    private boolean authSuccess;

    private LocalDateTime startAuth;

    @Column(nullable = false)
    private boolean confirm = false;

    private SellerCode(Builder builder){
        this.email = builder.email;
        this.authCode = builder.authCode;
        this.authSuccess = false;
        this.startAuth = LocalDateTime.now();
    }

    public static Builder email(@NotBlank String email){
        return new Builder(email);
    }

    public static class Builder{
        private String email;
        private String authCode;
        private boolean authCompleted;

        public Builder(String email){
            this.email = email;
        }

        public SellerCode code(String code){
            this.authCode = code;
            return new SellerCode(this);
        }

    }
}
