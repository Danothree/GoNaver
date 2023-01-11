package com.dano.kjm.domain.seller.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

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
public class MailSendService {

    private final JavaMailSenderImpl mailSender;
    private int authNumber;

    private int makeRandomNumber(){
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        return checkNum;
    }


}
