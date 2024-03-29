package com.project.elicegym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll() // 이 주소는 모든 접근 허용
                        .requestMatchers("/admin").hasRole("ADMIN") // 이 주소는 ADMIN 만 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // 이 주소 밑의 모든 주소는 ADMIN 과 USER 만 허용
                        .anyRequest().authenticated() // 이 외의 모든 주소는 로그인한 사용자만 접근 가능
                );
        http
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지로 이동
                        .loginProcessingUrl("/loginProc") // login 폼에서 전달하는 loginProc 페이지로 이동
                        .permitAll()
                );
        http
                .csrf((auth) -> auth.disable()); // 일단은 비활성화
        return http.build();
    }
}
