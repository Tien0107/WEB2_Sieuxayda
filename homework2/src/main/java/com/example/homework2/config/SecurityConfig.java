package com.example.homework2.config;

import com.example.homework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Cấu hình Spring Security
 * - Cho phép truy cập login/register mà không cần authentication
 * - Yêu cầu authentication cho tất cả các trang khác
 * - Cấu hình form login và logout
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Cấu hình AuthenticationProvider
     * Sử dụng UserService để load user details
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    
    /**
     * Cấu hình SecurityFilterChain
     * Định nghĩa các quy tắc bảo mật cho ứng dụng
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Cấu hình authorization
            .authorizeHttpRequests(authz -> authz
                // Cho phép truy cập các trang login, register và static resources
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                // Yêu cầu authentication cho tất cả các request khác
                .anyRequest().authenticated()
            )
            // Cấu hình form login
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            // Cấu hình logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            // Cấu hình authentication provider
            .authenticationProvider(authenticationProvider())
            // Tắt CSRF cho H2 console (chỉ dùng trong development)
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            // Cấu hình frame options cho H2 console
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
            
        return http.build();
    }
}
