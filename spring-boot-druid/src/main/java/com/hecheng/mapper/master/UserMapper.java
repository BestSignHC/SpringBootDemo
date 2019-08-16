package com.hecheng.mapper.master;

import com.hecheng.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM users where id = #{id}")  //另一种写法
    User findById(@Param("id") int id);
}
