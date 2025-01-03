package org.sist.sb06_sbb5.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

	// DTO. 입력창 전용임!
	
	@NotEmpty(message = "내용은 필수항목입니다!")
	private String content;
	
	
	
}
