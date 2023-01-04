package com.dano.kjm.domain.member.exception;

public class MemberDuplicatedException extends RuntimeException {

    public MemberDuplicatedException(String message) {
        super(message);
    }
}
