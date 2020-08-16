package com.study.springboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-20 22:14
 */
@Data
//@Entity
public class Users {

//    @Id
    private int id;
    private String username;
    private String password;
    private String name;
    private List<Product> products;
}