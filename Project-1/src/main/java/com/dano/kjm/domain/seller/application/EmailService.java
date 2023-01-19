package com.dano.kjm.domain.seller.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    private JavaMailSender mailSender ;
    private SimpleMailMessage message;

    public EmailService(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        message = new SimpleMailMessage();
    }

    public void setSubject(String subject) throws MessagingException {
        message.setSubject(subject);
    }

    public void setText(String text) throws MessagingException {
        message.setText(text);
    }

    public void From(String fromEmail) throws UnsupportedEncodingException, MessagingException {
        message.setFrom(fromEmail);
    }

    public void setTo(String toEmail) throws MessagingException {
        message.setTo(toEmail);
    }


    public void send(){
        mailSender.send(message);
    }

}
