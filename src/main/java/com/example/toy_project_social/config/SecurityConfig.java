package com.example.toy_project_social.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 학습 목적으로 CSRF 보호 기능 비활성화
                // 1. 페이지 접근 권한 설정
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        // "/post/create", "/post/fix/**" 등 글쓰기/수정 관련 경로는 인증(로그인)이 필요함
                        .requestMatchers("/post/create", "/post/fix/**", "/post/delete/**").authenticated()
                        // 그 외 모든 경로("/**")는 누구나 접근 가능
                        .anyRequest().permitAll())
                // 2. 로그인 설정
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login") // 로그인 페이지 URL
                        .defaultSuccessUrl("/post/list")) // 로그인 성공 시 이동할 기본 URL
                // 3. 로그아웃 설정
                .logout((logout) -> logout
                        .logoutUrl("/user/logout") // 로그아웃 처리 URL (더 간결한 방식)
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true)); // 로그아웃 시 세션 무효화

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화를 위한 BCryptPasswordEncoder를 Bean으로 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // 스프링 시큐리티의 인증을 담당하는 AuthenticationManager를 Bean으로 등록
        return authenticationConfiguration.getAuthenticationManager();
    }
}