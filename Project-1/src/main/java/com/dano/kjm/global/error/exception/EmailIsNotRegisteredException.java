package com.dano.kjm.global.error.exception;

/**
 * EmailIsNotRegisteredException.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.18
 */
public class EmailIsNotRegisteredException extends RuntimeException{

    public EmailIsNotRegisteredException() {
    }

    public EmailIsNotRegisteredException(String message) {
        super(message + " 은 등록된 이메일과 일치하지 않습니다.");
    }

}
