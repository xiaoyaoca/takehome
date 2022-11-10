package com.example.takehome.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class TakehomeConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(20, Duration.ofSeconds(30));
    }

}
