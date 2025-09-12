package com.example.homework2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Cấu hình riêng cho PasswordEncoder
 * Tách riêng để tránh circular dependency với SecurityConfig
 */
@Configuration
public class PasswordEncoderConfig {
    
    /**
     * Cấu hình PasswordEncoder sử dụng BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
