package com.study.springboot.repository;

import com.study.springboot.entity.Users;

import java.util.List;

public interface UsersRepository {
    List<Users> findAll();
    int deleteUsersById(int id);
}
