package org.sist.sb05_oracle_mybatis_thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sist.sb05_oracle_mybatis_thymeleaf.persistence") // 이 패키지의 모든 매퍼를 스캔함.
public class Sb05OracleMybatisThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb05OracleMybatisThymeleafApplication.class, args);
	}

}
