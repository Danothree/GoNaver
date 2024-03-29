package com.dano.kjm.domain.member.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("존재하지 않는 회원입니다.");
    }
}
