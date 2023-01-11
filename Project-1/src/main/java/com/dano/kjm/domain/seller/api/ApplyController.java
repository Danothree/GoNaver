package com.dano.kjm.domain.seller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApplyController.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.11
 */
@RequestMapping("/seller/apply")
@RestController
public class ApplyController {

    @GetMapping("/{email}")
    public ResponseEntity sendEmailCode(@PathVariable("email") String email){
        System.out.println("success" + email);
        return ResponseEntity.ok().build();
    }
}
