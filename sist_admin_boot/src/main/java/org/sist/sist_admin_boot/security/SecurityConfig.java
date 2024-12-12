package org.sist.sist_admin_boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
// 일단 로그인처리 없이 form 전송하기 위해 임시로 만든 시큐리티
public class SecurityConfig {

	@SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll())
                .headers((headers) -> headers // h2 깨진걸 출력하기위해
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                            XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))); // 모든 요청 허용

        return http.build();
    }
	
}