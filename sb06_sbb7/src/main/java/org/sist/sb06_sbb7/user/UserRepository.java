package org.sist.sb06_sbb7.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>{
	//Repository -> CRUDRepository ->  PagingAndSortingRepository -> JpaRepository
	
	//legacy 할 때 read()
	Optional<SiteUser> findByUsername(String username);
	
}
