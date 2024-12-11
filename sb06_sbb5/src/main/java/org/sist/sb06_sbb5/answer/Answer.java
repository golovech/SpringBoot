package org.sist.sb06_sbb5.answer;

import java.time.LocalDateTime;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Builder // setContent.. 같이 안써도 얘가 알아서 set 붙여줌
public class Answer {
	
	@Id // pk 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate ;
	
	@ManyToOne // n:1
	private Question question ; // FK로 줄거라면(조인), 우리가 만든 Question.java를 줘야함!! 
								// 실제 생성된 컬럼명 : question_id
	
	@ManyToOne // 한명이 여러개의 답변을 달수 있음.
	private SiteUser author;
}
 