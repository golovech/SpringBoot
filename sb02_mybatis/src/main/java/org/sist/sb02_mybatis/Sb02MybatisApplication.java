package org.sist.sb02_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sist.sb02_mybatis.persistence") // 이 패키지의 모든 매퍼를 스캔함.
public class Sb02MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb02MybatisApplication.class, args);
	}

}
