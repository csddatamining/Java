package com.study.springboot.bean;

/**
 * @author Cdu
 * @discription:
 * @create 2020-06-26 12:24
 */
public class Friend {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}