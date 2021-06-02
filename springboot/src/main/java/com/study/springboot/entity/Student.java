package com.study.springboot.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-01 17:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private int id;
    private String name;
    private String sex;
}