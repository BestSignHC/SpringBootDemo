package com.hc.domin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
public class User {
    @NotNull(message = "userId must not be null")
    @Range(min = 0L, max = 10L, message = "userId must >= 0 & <= 10")
    private Long userId = 0L;

    @NotNull(message = "name must not be null")
    @Length(max = 10, message = "the length of name must be less than 10")
    private String name = "";

    @NotNull(message = "age must not be null")
    @Range(min = 0, max = 60, message = "age must >= 0 & max <= 60")
    private Integer age = 0;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}