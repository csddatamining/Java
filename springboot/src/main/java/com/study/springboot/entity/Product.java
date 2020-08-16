package com.study.springboot.entity;

import lombok.Data;

/**
 * @author Cdu
 * @discription: 产品类
 * @create 2020-08-16 11:40
 */
@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String pic;
    private int uid;
}