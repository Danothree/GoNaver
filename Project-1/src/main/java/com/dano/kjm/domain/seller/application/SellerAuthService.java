package com.dano.kjm.domain.seller.application;

import com.dano.kjm.domain.seller.dao.SellerCodeRepository;
import com.dano.kjm.domain.seller.entity.SellerCode;
import com.dano.kjm.global.error.exception.EmailIsNotRegisteredException;
import com.dano.kjm.global.util.CodeMail;
import com.dano.kjm.global.util.TempCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

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
public class SellerAuthService {

    private EmailService emailService;
    private final JavaMailSender mailSender;
    private final SellerCodeRepository sellerCodeRepository;
    private final int KEY_SIZE = 6;

    @Transactional
    public void sendEmailCode(String toEmail, String userEmail) {
        Assert.notNull(toEmail,"the email value must not be null.");
        compareEmail(toEmail, userEmail);
        String authCode = createCode();
        sellerCodeRepository.save(SellerCode.email(toEmail)
                .code(authCode));
        sendEmail(toEmail, authCode);
    }

    private String createCode() {
        String authCode = TempCode.createCode(KEY_SIZE);
        return authCode;
    }

    private void sendEmail(String toEmail, String authCode) {
        try{
            emailService = new EmailService(mailSender);
            this.emailService.setSubject(CodeMail.SUBJECT);
            this.emailService.setText(CodeMail.text(authCode));
            this.emailService.From(CodeMail.FROM_EMAIL);
            this.emailService.setTo(toEmail);
            this.emailService.send();
        }catch (MessagingException | UnsupportedEncodingException e){
            log.error("Email Error: 이메일 변환 작업중 에외");
            e.getStackTrace();
        }
    }

    private void compareEmail(String sendEmail, String userEmail){
        if(!sendEmail.equals(userEmail)){
            log.error("로그인한 사용자 이메일과 일치하지 않은 이메일");
            throw new EmailIsNotRegisteredException(sendEmail);
        }
    }
}
