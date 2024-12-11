package org.sist.sb06_sbb6.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter // 회원 엔티티
public class SiteUser {

	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) // unique
	private String username;
	
	@Column(unique = true) // unique
	private String email;
	
	private String password;
	
}
