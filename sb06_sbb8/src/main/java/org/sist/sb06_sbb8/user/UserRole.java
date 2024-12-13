package org.sist.sb06_sbb8.user;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	}
	
	private String value;
	
}
//사용자 권한 열거형 ADMIN, USER 상수값
//@Enumerated(EnumType.STRING) 써도 될텐딩?