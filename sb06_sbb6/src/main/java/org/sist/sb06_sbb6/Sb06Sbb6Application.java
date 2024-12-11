package org.sist.sb06_sbb6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 앱 시작할때 맨 처음 실행됨. 
					   // @어노테이션 싹 스캔함!!
public class Sb06Sbb6Application { // 프로젝트명 + app... 

	public static void main(String[] args) {
		SpringApplication.run(Sb06Sbb6Application.class, args);
	}

}
