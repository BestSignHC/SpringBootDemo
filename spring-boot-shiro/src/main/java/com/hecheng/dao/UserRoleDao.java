package com.hecheng.dao;

import com.hecheng.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    void addUserRole(UserRole userRole);

    List<UserRole> findRoles(@Param("userId") Long userId);

    void removeUserRole(UserRole userRole);
}
