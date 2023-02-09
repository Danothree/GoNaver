package com.dano.kjm.global.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RedisProp.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.02.09
 */
@Getter
@Component
@Setter
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProp {
    private String host;
    private Integer port;
    private String password;
}
