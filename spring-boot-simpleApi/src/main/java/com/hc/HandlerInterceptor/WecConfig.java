package com.hc.HandlerInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WecConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;

    @Autowired
    private DemoInterceptor2 demoInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(demoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("")
                .order(0);

        registry.addInterceptor(demoInterceptor2)
                .addPathPatterns("/**")
                .excludePathPatterns("")
                .order(1);
    }
}
