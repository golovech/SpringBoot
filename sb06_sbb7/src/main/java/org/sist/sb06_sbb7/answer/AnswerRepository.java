package org.sist.sb06_sbb7.answer;

import org.springframework.data.jpa.repository.JpaRepository;
//																		<CRUD할 Entity, 그 테이블의 pk 타입>	
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	//JPQL 사용하면 Entity 사용
	//QueryDSL 사용
	
	
	
}
