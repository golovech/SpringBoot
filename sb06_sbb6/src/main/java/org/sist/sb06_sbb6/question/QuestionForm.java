package org.sist.sb06_sbb6.question;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// DTO 임.

@Setter
@Getter
public class QuestionForm {
	
	@NotEmpty(message = "제목 필수!!!!")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message = "> 내용 필수!!")
	private String content;

}
