package com.study.springboot;

import com.study.springboot.bean.Person;
import com.study.springboot.dao.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootStudyHelloApplicationTests {

	@Autowired
	private Person person;

	@Resource
	private StudentMapper studentMapper;

	@Test
	void contextLoads() {
		System.out.println(studentMapper.findAll());
        System.out.println("---------------");
        System.out.println(studentMapper.findStudentById(5));
//		System.out.println(person);
	}


}
