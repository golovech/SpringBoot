package org.sist.sb06_sbb5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 모든요청 URL이 스프링 시큐리티의 제어를 받게 함
				   // -> SecurityFilterChain 필터가 여기서 적용되는거임!
				   // == 스프링 시큐리티 활성화 어노테이션!!
public class SecurityConfig {

	// 스프링 레거시의, security-context.xml 와 같은 파일임.
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests
		((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		.headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
		return http.build();
	}
	
	
	
}
