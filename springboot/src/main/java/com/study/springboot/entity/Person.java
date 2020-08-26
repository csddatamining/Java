package com.study.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-26 21:09
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    @Id
    @GenericGenerator(name = "myuuid", strategy = "uuid")
    @GeneratedValue(generator = "myuuid")
    private String pid;
    @Column(unique = true)
    private String pname;
    @Column
    private String psex;
    @Column
    private Integer page;
    @Column
    private boolean getmarried;
}