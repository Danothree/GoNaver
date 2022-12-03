package com.dano.kjm.config;

import com.dano.kjm.config.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;

}
