package com.dano.kjm.domain.member.api.advice;

import com.dano.kjm.domain.member.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity userNotFound(UserNotFoundException exception, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timeStamp", LocalDateTime.now());
        body.put("message", "존재하지 않는 회원입니다.");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }
}
