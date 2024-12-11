package org.sist.sb06_sbb6.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// == DAOImpl!!!!!!!!!!!!!!!!!!!!
public interface UserRepository extends JpaRepository<SiteUser, Long> {

	// 1. 쿼리메서드, 2. 쿼리어노테이션, 3.쿼리DSL 사용가능
	
	// 왜 optional? optional은 언제 주는걸까..
	Optional<SiteUser> findByUsername(String username);
	//					관스벚ㄱ으로 이렇게
	//				   findBy + 필드명(소문자면 소문자, 대문자면 대문자)
	
}
