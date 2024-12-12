package org.sist.sb06_sbb7.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	@NotBlank(message = "사용자 id는 필수항목입니다~")
	@Size(max = 25, min = 3)
	private String username;
	
	@NotBlank(message = "비밀번호는 필수항목입니다~")
	private String password1;
	@NotBlank(message = "비밀번호확인은 필수입니다~")
	private String password2;
	
	@NotBlank(message = "이메일은 필수항목입니다~")
	@Email
	private String email;
	

}
