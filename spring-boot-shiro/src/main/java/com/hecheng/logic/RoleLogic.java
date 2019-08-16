package com.hecheng.logic;

import com.hecheng.dao.RoleDao;
import com.hecheng.dao.UserRoleDao;
import com.hecheng.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleLogic {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    public void deleteRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleDao.removeUserRole(userRole);

        roleDao.deleteRole(roleId);
    }
}
