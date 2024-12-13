package org.sist.sb06_sbb8.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
//회원 Entity
public class SiteUser {
	
	@Id
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	//다른 옵션 줄 거 아니면 @column 생략해도 돼
	private String password;
	
	@Column(unique = true)
	private String email;
}
