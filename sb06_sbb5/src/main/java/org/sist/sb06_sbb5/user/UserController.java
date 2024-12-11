package org.sist.sb06_sbb5.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	
	// 회원가입 버튼 누를때..
	@GetMapping("signup")
	public String signup(UserCreateForm userCreateForm) {
		return "/user/signup_form";
	}
	
	@PostMapping("signup")
	public String signup(
			@Valid UserCreateForm userCreateForm, 
			BindingResult bindingResult) { // 요청 전송하여 유효성 검사.
		
		// 유효성 검사
		if(bindingResult.hasErrors()) {
			return "/user/signup_form";
		}
		
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) { // 비밀번호 1,2 검사
			bindingResult.rejectValue("password2", "passwordError!!", "비밀번호가 일치하지 않습니다.");
			return "/user/signup_form";
			
		}
		try {
			// 유효성 검사 성공시
			this.userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
			
		} catch (DataIntegrityViolationException e) {
			// 유효성 검사 실패시
			// 문제점.. PK(ID), UK 둘다 같은 메세지가 출력됨. 분기해야할듯~~
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다!!");
			return "/user/signup_form";
		} catch (Exception e) {
			// 유효성 검사 실패시
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "/user/signup_form";
		}
		
		
		
		return "redirect:/"; // main페이지 간다는 뜻
	}
	
	@GetMapping("login")
	public String login() {
		return "/user/login_form"; // html이름
	}
	
}
