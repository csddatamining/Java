package com.study.springboot.repository;

import com.study.springboot.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-05 0:02
 */
@Repository
public class UsersRepositoryImp implements UsersRepository {

    static List<Users> users = new ArrayList<>();
    static {
        Collections.addAll(users,new Users(1001, "zhangsan", "123456", "张三","男"),
                new Users(1002, "lisi", "123456", "李四","男"),
                new Users(1003, "wangwu", "123456", "王五","男"),
                new Users(1004, "zhaoliu", "123456", "赵六","男"));
    }
    @Override
    public List<Users> findAll() {
        return users;
    }

    @Override
    public int deleteUsersById(int id) {
        int num = 0;
        Iterator<Users> iterator = users.iterator();
        while (iterator.hasNext()){
            Users users = iterator.next();
            if (users.getId() == id){
                iterator.remove();
                num = 1;
            }
        }

        return num;
    }
}