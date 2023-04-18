package com.dano.kjm.global.config;

import com.dano.kjm.global.config.security.SecurityMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String uploadImgPath;

    public WebConfig(@Value("${custom.itemImgLocation}") String uploadImgPath) {
        this.uploadImgPath = uploadImgPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String property = System.getProperty("user.home");
        registry.addResourceHandler("/seller/items/outer/**","/seller/sim@danu.com/**")
                .addResourceLocations("file:///"+property+"/");
    }
}