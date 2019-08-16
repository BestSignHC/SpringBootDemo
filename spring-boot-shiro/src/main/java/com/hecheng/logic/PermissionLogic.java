package com.hecheng.logic;

import com.hecheng.dao.PermissionDao;
import com.hecheng.dao.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionLogic {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    public void delete(Long permissionId) {
        rolePermissionDao.removeByPermissionId(permissionId);

        permissionDao.deletePermission(permissionId);
    }
}
