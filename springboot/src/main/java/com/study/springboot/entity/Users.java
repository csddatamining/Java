package com.study.springboot.entity;

import org.springframework.stereotype.Component;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-04 23:57
 */
@Component
public class Users {
    private int id;
    private String username;
    private String password;
    private String name;
    private String userSex;

    public Users() {
    }

    public Users(int id, String username, String password, String name, String userSex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.userSex = userSex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}