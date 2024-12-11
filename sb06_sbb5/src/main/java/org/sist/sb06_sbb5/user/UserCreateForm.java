package org.sist.sb06_sbb5.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// dto


@Setter
@Getter
public class UserCreateForm {

	@Size(max = 25, min = 3)
	@NotEmpty(message = "사용자ID는 필수!필수!")
	private String username;
	
	@NotEmpty(message = "비밀번호 필수!")
	private String password1;
	@NotEmpty(message = "비밀번호 확인 필수!")
	private String password2; // 중복테스트
	
	
	@NotEmpty(message = "이메일 입력 필수!")
	@Email
	private String email;
	
}
