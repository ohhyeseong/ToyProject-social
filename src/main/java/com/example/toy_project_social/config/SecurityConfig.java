package com.example.toy_project_social.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (임시)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // 모든 경로에 대한 접근 허용 (임시)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}