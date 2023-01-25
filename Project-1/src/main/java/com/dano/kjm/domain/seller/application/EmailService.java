package com.dano.kjm.domain.seller.application;

import com.dano.kjm.global.util.CodeMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

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

    private final JavaMailSender mailSender ;
    private SimpleMailMessage message = new SimpleMailMessage();;

    private void setSubject(String subject) throws MessagingException {
        message.setSubject(subject);
    }

    private void setText(String text) throws MessagingException {
        message.setText(text);
    }

    private void From(String fromEmail) throws UnsupportedEncodingException, MessagingException {
        message.setFrom(fromEmail);
    }

    private void setTo(String toEmail) throws MessagingException {
        message.setTo(toEmail);
    }

    private void send(){
        mailSender.send(message);
    }

    @Async
    public void sendCode(String email, String code) throws MessagingException, UnsupportedEncodingException {
        setSubject(CodeMail.SUBJECT);
        setText(CodeMail.text(code));
        From(CodeMail.FROM_EMAIL);
        setTo(email);
        send();
    }

}
