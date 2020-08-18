package com.study.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-20 22:14
 */
@Data
@Entity
//实体类的类名和表名的关系
@Table(name = "tb_user")
public class Users {
    @Id //主键id
//    //UUID随机数生成方式
//    @GenericGenerator(name = "myuuid", strategy = "uuid")
//    @GeneratedValue(generator = "myuuid")
    //自增长生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;
    @Column
    private String password;
//    private String name;
//    private List<Product> products;
}