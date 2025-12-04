package com.goormthon.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ★★★ CORS 활성화 (필수) ★★★
                .cors(Customizer.withDefaults())

                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())

                // URL 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/api/v3/api-docs/**",
                                "/webjars/**"
                        ).permitAll()
                        .anyRequest().permitAll()  // ← 인증 안 쓰면 이게 더 적합
                );

        return http.build();
    }
}
