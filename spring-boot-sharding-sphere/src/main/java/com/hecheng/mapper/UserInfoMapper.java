package com.hecheng.mapper;

import com.hecheng.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    public void add(UserInfo userInfo);

    public UserInfo selectById(@Param("userId") Long userId);
}
