package org.sist.sb06_sbb7.security;

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
@EnableWebSecurity//FilterChain을 위해 붙인거야~
//모든 요청 url이 spring security의 제어를 받게 함,
//			SecurityFilterChain filter가 적용되어 활성화되게 만들어
@EnableMethodSecurity(prePostEnabled = true)
//@PreAuthorize("isAuthenticated()")가 쓸 수 있게 해주는 spring security 설정
public class SecurityConfig{
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		.csrf((csrf) -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		.headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin)->formLogin
			.loginPage("/user/login")
			.defaultSuccessUrl("/"))
		.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true));
		
		return http.build();
	}
	
	/*http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
	 	.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())*/
	
	//위는 spring legacy에서 <security:intercept-url pattern="/**" access="permitAll" /> 이거랑 같아
	//로그인 안해도 모든 요청 허락
	
	
	 /*
	  
	 <security:form-login
  	login-page="/joinus/login.htm"
  	default-target-url="/index.htm"
  	authentication-failure-url="/joinus/login.htm?error=true"
  	authentication-success-handler-ref="customLoginSuccessHandler"
  /> 
   
   이거랑  
   .formLogin((formLogin)->formLogin
			.loginPage("/user/login")
			.defaultSuccessUrl("/"));
			이거 같음
   */
	
	
	/*
	 
	  <security:access-denied-handler ref="customAccessDeniedHandler" />
	     <security:logout logout-url="/joinus/logout.htm"
	     invalidate-session="true"
	     logout-success-url="/" 
     	/>
	  
	  예전에 했던 위 코딩과
	  .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true));
                이게 같아
	  
	 */
	
	
	
	
	
	
	
	//AuthenticationManage bean객체 생성, spring security 인증 처리하는 담당 객체
	//인증될 때 우리가 만든 UserSecurityService와 PasswordEncoder가 자동으로 내부에서 사용된다.
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	
	
	
	
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*위는 legacy에서 했던 아래와 같아
	 * <security:authentication-provider user-service-ref="customUserDetailsService">
	    	<security:password-encoder ref="bCryptPasswordEncoder" />
	  	</security:authentication-provider>
	  */
}//class
