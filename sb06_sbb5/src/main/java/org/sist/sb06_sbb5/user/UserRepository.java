package org.sist.sb06_sbb5.user;

import org.springframework.data.jpa.repository.JpaRepository;

// == DAOImpl!!!!!!!!!!!!!!!!!!!!
public interface UserRepository extends JpaRepository<SiteUser, Long> {

	// 1. 쿼리메서드, 2. 쿼리어노테이션, 3.쿼리DSL 사용가능
	
	
	
}
