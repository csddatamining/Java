package com.study.springboot.repository;

import com.study.springboot.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users")
public interface UsersRepository {

    @Select("select count(1) from users where username=#{users.username} and password=#{users.password}")
    int login(@Param("users") Users users);

    @Select("select * from Users")
    List<Users> findAll();


}
