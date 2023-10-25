package com.ssafy.hanol.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // rest API , crsf 사용 X
        http.csrf()
                .disable();

        // 시큐리티 기본 login 방식 사용 X
        http.formLogin()
                .disable();

        http.cors(AbstractHttpConfigurer::disable);



        return http.build();
    }
}
