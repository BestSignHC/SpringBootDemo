package com.hecheng.domain;

/**
 * Created by ChengH on 2018/3/23.
 */

public class User {
    private int id =0;
    private String name = "";
    private int age = 0;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
