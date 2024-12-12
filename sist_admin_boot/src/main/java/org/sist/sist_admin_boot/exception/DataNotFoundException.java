package org.sist.sist_admin_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "entity not foundCake!")
public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}

}

// 질문 id 가 존재하지 않을 때 발생하는 사용자 예외 클래스
// [DataNotFoundException]
// 위의 예외가 발생하면, 스프링 부트에서는 
// HTTP상태 코드 + 예외발생이유를 포함하여,, 응답객체를 생성할 수 있음.