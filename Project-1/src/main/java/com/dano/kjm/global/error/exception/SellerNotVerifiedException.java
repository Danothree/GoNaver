package com.dano.kjm.global.error.exception;

/**
 * SellerNotVerifiedException.java
 * 판매자 인증이 안된 사용자를 판매자 등록할 때, 나오는 에러
 *
 * @author sgh
 * @since 2023.01.17
 */
public class SellerNotVerifiedException extends RuntimeException{
    public SellerNotVerifiedException() {
    }

    public SellerNotVerifiedException(String message) {
        super(message);
    }
}
