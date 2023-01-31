package com.dano.kjm.domain.seller.api;

import com.dano.kjm.domain.seller.application.SellerApplyService;
import com.dano.kjm.global.config.security.SecurityMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    private final SellerApplyService sellerApplyService;

    @PostMapping("/{email}")
    public ResponseEntity sendEmailCode(@PathVariable("email") String email,
                                        @AuthenticationPrincipal SecurityMember member)  {
        sellerApplyService.sendEmailCode(email, member.getEmail());
        return ResponseEntity.ok().build();
    }
}
