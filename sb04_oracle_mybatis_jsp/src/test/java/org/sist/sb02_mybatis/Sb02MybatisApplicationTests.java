package org.sist.sb02_mybatis;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.sist.sb04_oracle_mybatis_jsp.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@Log
@SpringBootTest
class Sb02MybatisApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	TimeMapper timeMapper; // 주입받아서..
	
	@Test
	void timeMapperTest() {
		System.out.println("현재시간 ... "+ this.timeMapper.getTime());
	}
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void testConnection() {
		
		try (Connection con = this.dataSource.getConnection()){
			log.info(">> Connection : "+con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
