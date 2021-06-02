package com.study.springboot;

import com.study.springboot.dao.StudentMapper;
import com.study.springboot.entity.Student;
import com.study.springboot.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-01 18:13
 */
public class ProjectTest {
        @Test
    public void testSelect(){
        //使用的是Mybatis的原始API--基于statementID的写法
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        Object o = sqlSession.selectOne("com.study.springboot.dao.StudentMapper.findStudentById", 5);
        System.out.println(o);
    }

    public void testSelect2(){
        //使用的是基于mapper接口的写法
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu = mapper.findStudentsByName("张");
        System.out.println(stu);
        SqlSessionFactoryUtil.closeSession(sqlSession);
    }

    public void testFindAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> stus = mapper.findAll();
        System.out.println(stus);
        SqlSessionFactoryUtil.closeSession(sqlSession);
    }

//    @Test
//    public void testInsert(){
//        Student stu = new Student("刘德华","boy");
//        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//        mapper.insertStudent(stu);
//        SqlSessionFactoryUtil.closeSession(sqlSession);
//    }

    @Test
    public void testUpdate(){
        Student stu = new Student(1004,"刘德华","girl");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudent(stu);
        SqlSessionFactoryUtil.closeSession(sqlSession);
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStudent(1004);
        SqlSessionFactoryUtil.closeSession(sqlSession);
    }
}