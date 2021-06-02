package com.study.springboot.service;

import com.study.springboot.entity.Student;
import com.study.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cdu
 * @discription:
 * @create 2020-09-06 20:24
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(String name, String sex) {
        Student student = Student.builder().name(name).sex(sex).build();
        studentRepository.insertStudent(student);
        return studentRepository.findStudentByNear();
    }
}