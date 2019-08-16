package com.hecheng.dao;

import com.hecheng.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    void addRolePermission(RolePermission rolePermission);

    List<RolePermission> findByRole(@Param("roleId") Long roleId);

    void remove(RolePermission rolePermission);

    void removeByPermissionId(@Param("permissionId") Long permissionId);
}
