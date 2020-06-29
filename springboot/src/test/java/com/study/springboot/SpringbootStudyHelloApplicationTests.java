package com.study.springboot;

import com.study.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootStudyHelloApplicationTests {

	@Autowired
	private Person person;
	@Test
	void contextLoads() {
		System.out.println(person);
	}

}
