package org.sist.sb06_sbb6.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 모든요청 URL이 스프링 시큐리티의 제어를 받게 함
				   // -> SecurityFilterChain 필터가 여기서 적용되는거임!
				   // == 스프링 시큐리티 활성화 어노테이션!!

@EnableMethodSecurity(prePostEnabled = true)
	// @PreAuthorize("isAuthenticated()") 을 동작하게 하는 스프링 시큐리티 설정. 꼭!!!넣어야 작동함
public class SecurityConfig {

	// 스프링 레거시의, security-context.xml 와 같은 파일임.
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests
		((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // permitAll() : 모두접근가능하게
		.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))) // h2 접속하기위해
		.headers((headers) -> headers // h2 깨진걸 출력하기위해
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin)->formLogin
				.loginPage("/user/login")
				.defaultSuccessUrl("/")) // 로그인처리
		.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)); // 로그아웃 처리
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // bean 객체 생성하여 돌려주는것.
	}
	
	// AuthenticationManager 빈객체 생성
	// 스프링 시큐리티의 인증처리 최고담당자
	// UserSecurityService.java 와 PasswordEncoder가 자동으로 내부적으로 사용됨
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
}
