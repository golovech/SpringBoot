package org.sist.sb06_sbb8.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//입력 받는 애들 담는 거니까 DTO
public class QuestionForm {
	@NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max = 200)
	private String subject;
	
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;
}
