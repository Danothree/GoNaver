package com.dano.kjm.global.util;

import lombok.Getter;

/**
 * MailHtml.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.18
 */
@Getter
public class CodeMail {
    public static String SUBJECT = "Best Ecommerce 인증 메일";
    public static String FROM_EMAIL = "subgyuhwan19@gmail.com";
    public static String FROM_NAME = "goodGuy";

    public static String text(String emailKey){
        return new StringBuilder()
                .append("Best Ecommerce 인증 메일 ")
                .append("이메일 인증 코드는 ")
                .append(emailKey)
                .append(" 입니다. ")
                .append("감사합니다.")
                .toString();
    }
}
