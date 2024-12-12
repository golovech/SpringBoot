package org.sist.sb06_sbb7.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "/user/signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		
		//유효성 검사
		if(bindingResult.hasErrors()) {
			return "/user/signup_form";
			}//if
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다");
			//bindingResult.rejectValue("필드명", "에러코드", "기본 에러 메시지");
			return "/user/signup_form";
		}
		
		
		try {
			this.userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
			return "redirect:/";
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("singupFailed", "이미 등록된 사용자입니다.");
			return "/user/signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("singupFailed", e.getMessage());
			return "/user/signup_form";
		}
		
	}	
		@GetMapping("/login")
		public String login() {
			return "/user/login_form";
		}
		

		@GetMapping("/logout")
		public String logout() {
			return "/user/login_form";
		}
	
}
