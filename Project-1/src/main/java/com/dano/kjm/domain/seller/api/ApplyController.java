package com.dano.kjm.domain.seller.api;

import com.dano.kjm.domain.seller.application.SellerAuthService;
import com.dano.kjm.global.config.security.SecurityMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * ApplyController.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.11
 */
@RequestMapping("/seller/apply")
@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final SellerAuthService sellerAuthService;

    @GetMapping("/{email}")
    public ResponseEntity sendEmailCode(@PathVariable("email") String email,
                                        @AuthenticationPrincipal SecurityMember member) throws MessagingException {
        sellerAuthService.sendEmailCode(email, member.getEmail());
        System.out.println("success " + email);
        return ResponseEntity.ok().build();
    }
}
