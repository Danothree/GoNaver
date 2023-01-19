//package com.dano.kjm.global.config;
//
//import com.dano.kjm.global.prop.MailProp;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
///**
// * MailConfig.java
// * Class 설명을 작성하세요.
// *
// * @author sgh
// * @since 2023.01.11
// */
//@Configuration
//@RequiredArgsConstructor
//public class MailConfig {
//
//    private final MailProp mailProp;
//
//    @Bean
//    public JavaMailSender mailSender(){
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.transport.protocol", "smtp");
//        mailProperties.put("mail.smtp.auth", "true");
//        mailProperties.put("mail.smtp.starttls.enable", "true");
//        mailProperties.put("mail.smtp.debug", "true");
//        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost(mailProp.getHost());
//        mailSender.setPort(mailProp.getPort());
//        mailSender.setUsername(mailProp.getUsername());
//        mailSender.setPassword(mailProp.getPassword());
//        mailSender.setDefaultEncoding(mailProp.getEncoding());
//        return mailSender;
//    }
//
//}
