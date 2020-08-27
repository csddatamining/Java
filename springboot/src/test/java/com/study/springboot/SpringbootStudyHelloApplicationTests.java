package com.study.springboot;

import com.study.springboot.entity.Person;
import com.study.springboot.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class SpringbootStudyHelloApplicationTests {

	@Resource
	private PersonRepository personRepository;

	@Test
	void contextLoads() {
		System.out.println(personRepository.findPersonByBname("三国演义"));
//		personRepository.updatePerson(Person.builder().pid("402881e6742afd7f01742afdca280001").pname("haha").page(43).psex("男").getmarried(false).build());
//		personRepository.deleteByName("zhang");
//		System.out.println(personRepository.findAllByPageIsLessThanEqual(22));
//		System.out.println(personRepository.findAllByPageBetweenAndPsexEquals(20,22 ,"男"));
//		System.out.println(personRepository.findAllByGetmarriedIsTrueAndPsexEquals("男"));
	}

	private void initPersons() {
		List<Person> personList = new ArrayList<>();
		Collections.addAll(personList,
				Person.builder().pname("zhangsan").psex("男").page(22).getmarried(true).build(),
				Person.builder().pname("lisi").psex("男").page(22).getmarried(true).build(),
				Person.builder().pname("wangwu").psex("男").page(23).getmarried(true).build(),
				Person.builder().pname("zhaoliu").psex("男").page(25).getmarried(true).build(),
				Person.builder().pname("sunyi").psex("女").page(27).getmarried(true).build()
				);
		personRepository.saveAll(personList);
	}


}
