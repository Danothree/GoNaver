package com.dano.kjm.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
        registry.addResourceHandler("/items/**/upload/**")
                .addResourceLocations("file:///"+property);
    }
}
