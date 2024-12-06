package org.sist.sb06_sbb2.answer;

import java.time.LocalDateTime;

import org.sist.sb06_sbb2.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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
}
 