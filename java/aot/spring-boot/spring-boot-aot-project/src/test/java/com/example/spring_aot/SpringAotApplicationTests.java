package com.example.spring_aot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class SpringAotApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("Hello World");
	}

	@Configuration
	public static class Config {}

}
