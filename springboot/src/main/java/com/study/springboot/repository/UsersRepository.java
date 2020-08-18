package com.study.springboot.repository;

import com.study.springboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(path = "users")
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findUsersByUsernameLike(String username);
//    @Select("select count(1) from users where username=#{users.username} and password=#{users.password}")
//    int login(@Param("users") Users users);
//
//    @Select("select * from Users")
//    List<Users> findAll();


}
