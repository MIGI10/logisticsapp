package com.hackathon.inditex.Config;

import com.hackathon.inditex.Interceptor.RateLimitingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SecurityHeadersInterceptor securityHeadersInterceptor;
    private final RateLimitingInterceptor rateLimitingInterceptor;

    @Autowired
    public WebConfig(SecurityHeadersInterceptor securityHeadersInterceptor, RateLimitingInterceptor rateLimitingInterceptor) {
        this.securityHeadersInterceptor = securityHeadersInterceptor;
        this.rateLimitingInterceptor = rateLimitingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityHeadersInterceptor);
        registry.addInterceptor(rateLimitingInterceptor)
                .addPathPatterns("/api/**");
    }
}
