package com.dano.kjm.global.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MailProp.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.17
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mail" )
public class MailProp {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String encoding;
}
