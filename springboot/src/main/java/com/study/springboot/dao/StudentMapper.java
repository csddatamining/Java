package com.study.springboot.dao;

import com.study.springboot.entity.Student;

import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-01 17:55
 */
public interface StudentMapper {
    Student findStudentById(Integer id);

    Student findStudentsByName(String name);

    void insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer id);

    List<Student> findAll();


    /**注解版
     @Delete("delete from student where id=#{id}")
     int deleteStudentById(Integer id);

     @Update("update student set name=#{name},sex=#{sex} where id=#{id}")
     int updateStudent(Student student);

     @Insert("insert into student(name,sex) values(#{name},#{sex})")
     int insertStudent(Student entity);

     @Select("SELECT id, name, sex from student where name like '%${value}%'")
     List<Student> selectStudentsByName(String name);

     @Select("select id, name, sex from student where id=#{id}")
     Student selectStudentById(int id);
     */

}