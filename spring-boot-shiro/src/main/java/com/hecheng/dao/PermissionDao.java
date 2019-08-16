package com.hecheng.dao;

import com.hecheng.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionDao {

    public void createPermission(Permission permission);

    public void deletePermission(@Param("permissionId") Long permissionId);

    public Permission find(@Param("permissionId") Long permissionId);
}