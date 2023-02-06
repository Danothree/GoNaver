package com.dano.kjm.domain.seller.application;

import com.dano.kjm.global.util.CodeMail;
import com.dano.kjm.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * MailSendService.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final Long VALID_TIME = 1000 * 60 * 5L;

    private final RedisUtil redisUtil;
    private final JavaMailSender mailSender ;
    private SimpleMailMessage message = new SimpleMailMessage();;

    private void setSubject(String subject){
        message.setSubject(subject);
    }

    private void setText(String text) {
        message.setText(text);
    }

    private void From(String fromEmail){
        message.setFrom(fromEmail);
    }

    private void setTo(String toEmail)  {
        message.setTo(toEmail);
    }

    private void send(){
        mailSender.send(message);
    }

    @Async
    public void sendCode(String email, String code)  {
        redisUtil.setDataExpire(code, email, VALID_TIME);
        setSubject(CodeMail.SUBJECT);
        setText(CodeMail.text(code));
        From(CodeMail.FROM_EMAIL);
        setTo(email);
        send();
    }

}
