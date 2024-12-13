package org.sist.sb06_sbb8.answer;

import java.time.LocalDateTime;
import java.util.Set;

import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Builder //setter 말고 이거 써도 돼~
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")//문자열 길이 제한 없어져(마치 oracle의 clob같이 길게 나열되어도 ㅇㅋ)
	private String content;
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question;//우리꺼 import한 거임~
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany//답변과 회원은 M:N 이처럼 M:N일 때는 테이블이 하나 만들어져 확인할 것 PK가 복합키야
	private Set<SiteUser> voter;//좋아요는 한 번 밖에 못 누르니까 Set으로
}
