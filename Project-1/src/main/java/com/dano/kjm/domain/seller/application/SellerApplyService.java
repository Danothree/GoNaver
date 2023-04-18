package com.dano.kjm.domain.seller.application;

import com.dano.kjm.domain.seller.dao.SellerCodeRepository;
import com.dano.kjm.domain.seller.entity.SellerCode;
import com.dano.kjm.global.error.exception.EmailIsNotRegisteredException;
import com.dano.kjm.global.util.TempCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * SellerAuthController.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SellerApplyService {

    private final EmailService emailService;
    private final SellerCodeRepository sellerCodeRepository;
    private final int KEY_SIZE = 6;

    @Transactional
    public void sendEmailCode(String toEmail, String userEmail) {
        Assert.notNull(toEmail, "the email value must not be null.");
        compareEmail(toEmail, userEmail);

        String authCode = createCode();

        sendEmail(toEmail, authCode);
        saveEmailAndCode(toEmail, authCode);
    }

    private String createCode() {
        return TempCode.createCode(KEY_SIZE);
    }

    private void sendEmail(String email, String authCode) {
        emailService.sendCode(email, authCode);
    }

    private void compareEmail(String sendEmail, String userEmail) {
        if (!sendEmail.equals(userEmail)) {
            log.error("로그인한 사용자 이메일과 일치하지 않은 이메일");
            throw new EmailIsNotRegisteredException(sendEmail);
        }
    }

    private void saveEmailAndCode(String toEmail, String authCode) {
        SellerCode sellerCode = sellerCodeRepository.findAllByEmail(toEmail).orElse(null);
        if (sellerCode != null) {
            sellerCode.changeAuthCode(authCode);
        } else {
            sellerCodeRepository.save(SellerCode.email(toEmail)
                    .code(authCode));
        }
    }

    public String codeCheck(String emailCode, String email) {
        SellerCode sellerCode = sellerCodeRepository.findAllByEmail(email).orElse(null);
        if (sellerCode != null && emailCode.equals(sellerCode.getAuthCode())) {
            sellerCode.certified();
            return sellerCode.getAuthCode();
        } else {
            return "failed";
        }
    }

}
