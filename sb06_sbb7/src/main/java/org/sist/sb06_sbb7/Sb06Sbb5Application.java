package org.sist.sb06_sbb7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//@EntityScan(basePackages = "org.sist.sb06_sbb6.question") 이거 @Entity쓰지 않아도 이거로 scan가능
@SpringBootApplication//이게 component scan 내포함
public class Sb06Sbb5Application {

	public static void main(String[] args) {
		SpringApplication.run(Sb06Sbb5Application.class, args);
	}

}
