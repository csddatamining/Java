package com.study.springboot.controller;

import com.study.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cdu
 * @discription:
 * @create 2020-09-11 22:28
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("stu/add")
    public Object addStudent(@Param("name") String name, @Param("sex") String sex) {
        return studentService.addStudent(name, sex);
    }
}