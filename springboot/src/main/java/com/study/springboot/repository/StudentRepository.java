package com.study.springboot.repository;

import com.study.springboot.entity.Student;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Cdu
 * @discription:
 * @create 2020-09-06 20:17
 */
@Repository
public interface StudentRepository {

    @Update(value = "insert into student(name,sex) values(#{student.name},#{student.sex})")
    void insertStudent(@Param("student")Student student);

    @Select(value = "select * from student where id =(select @@IDENTITY)")
    Student findStudentByNear();
}