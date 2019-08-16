package com.hecheng.dao;

import com.hecheng.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface UserDao {

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(@Param("userId") Long userId);

    User findOne(@Param("userId") Long userId);

    User findByUsername(@Param("username") String username);
}
