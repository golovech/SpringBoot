package org.sist.sb06_sbb6.user;

import lombok.Getter;

// 열거형!! 이넘
@Getter // 값을 읽어가기만 할거니까, GETTER만 필요함.
public enum UserRole {
	
	// ADMIN이 오면 ADMIN권한을 주고,,, 하는 역할.
	
	ADMIN("ROLE_ADMIN") , USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	}
	
	private String value;
}





/*

사용자 권한종류를, 열거형으로 만듦.. 
	: ADMIN, USER 상수의 값으로 "ROLE_ADMIN" / "ROLE_USER" 설정.
	
	이넘은 어떨때 사용할까?
	 -> 값을 읽는다. 어떻게 여기걸 읽을까?
	
*/