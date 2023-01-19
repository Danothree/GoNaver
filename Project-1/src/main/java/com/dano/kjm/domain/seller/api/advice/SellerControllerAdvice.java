package com.dano.kjm.domain.seller.api.advice;

import com.dano.kjm.domain.seller.api.ApplyController;
import com.dano.kjm.global.error.exception.EmailIsNotRegisteredException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * SellerControllerAdvice.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.18
 */
@RestControllerAdvice(basePackageClasses = {ApplyController.class})
public class SellerControllerAdvice {

    @ExceptionHandler(EmailIsNotRegisteredException.class)
    public ResponseEntity emailIsNotRegisteredException(EmailIsNotRegisteredException e){
        return ResponseEntity.badRequest().body("invalid email");
    }
}
