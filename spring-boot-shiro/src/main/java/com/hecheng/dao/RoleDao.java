package com.hecheng.dao;

import com.hecheng.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleDao {

    public void createRole(Role role);

    public void deleteRole(@Param("roleId") Long roleId);

    public Role find(@Param("roleId") Long roleId);
}
