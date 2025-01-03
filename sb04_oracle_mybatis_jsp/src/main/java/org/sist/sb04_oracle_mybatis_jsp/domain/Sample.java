package org.sist.sb04_oracle_mybatis_jsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity // pom.xml에 jpa 추가하고, 프로펄티에서도 추가해줘야 사용가능
@Table(name="tbl_sample") // name안주면, 클래스명이 테이블명이 됨
public class Sample {

	@Id // sno를 PK로 사용
	// @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스처럼 sno가 자동 증가 -> 오라클에서는 못사용함.. 대신 시퀀스를 만들자
	@SequenceGenerator(name = "seq_tablsample", sequenceName = "seq_tablsample", initialValue = 1, allocationSize = 1) 
	// 시퀀스만들기~
	// initialValue = 1, allocationSize = 1  1씩 증가.
	@GeneratedValue(generator = "seq_tablsample", strategy = GenerationType.SEQUENCE )
	// 시퀀스명 주고, 시퀀스라고 명시
	private Long sno;
	
	private String col1;
	private String col2;
	
	/*
	Hibernate: create table tbl_sample (sno number(19,0) not null, col1 varchar2(255 char), col2 varchar2(255 char), primary key (sno))
	Hibernate: create sequence seq_tablsample start with 1 increment by 1 
	 */
	
}
