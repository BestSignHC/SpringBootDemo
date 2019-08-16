package com.hecheng.logic;

import com.hecheng.dao.RoleDao;
import com.hecheng.dao.UserDao;
import com.hecheng.dao.UserRoleDao;
import com.hecheng.entity.Role;
import com.hecheng.entity.User;
import com.hecheng.entity.UserRole;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRoleLogic {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId : roleIds) {
            if (exists(userId, roleId)) {
                continue;
            }

            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleDao.addUserRole(userRole);
        }
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }

        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleDao.removeUserRole(userRole);
        }
    }

    public Set<String> findRoles(String username) {
        User user = userDao.findByUsername(username);
        List<UserRole> roles = userRoleDao.findRoles(user.getId());

        Set<String> result = new HashSet<>();

        for (UserRole role : roles) {
            Role role1 = roleDao.find(role.getRoleId());
            result.add(role1.getRole());
        }
        return result;
    }

    public boolean exists(Long userId, Long roleId) {
        List<UserRole> roles = userRoleDao.findRoles(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }

        for (UserRole userRole : roles) {
            if (userRole.getRoleId() == roleId) {
                return true;
            }
        }
        return false;
    }
}
