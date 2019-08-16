package com.hecheng.logic;

import com.hecheng.dao.PermissionDao;
import com.hecheng.dao.RolePermissionDao;
import com.hecheng.dao.UserDao;
import com.hecheng.dao.UserRoleDao;
import com.hecheng.entity.Permission;
import com.hecheng.entity.RolePermission;
import com.hecheng.entity.User;
import com.hecheng.entity.UserRole;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RolePermissionLogic {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PermissionDao permissionDao;

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        for(Long permissionId : permissionIds) {
            if (exists(roleId, permissionId)) {
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);

            rolePermissionDao.addRolePermission(rolePermission);
        }
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        for(Long permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);

            rolePermissionDao.remove(rolePermission);
        }
    }

    public Set<String> findPermissions(String username) {
        User user = userDao.findByUsername(username);
        List<UserRole> roles = userRoleDao.findRoles(user.getId());

        Set<String> result = new HashSet<>();

        for (UserRole role : roles) {
            List<RolePermission> permissions = rolePermissionDao.findByRole(role.getRoleId());

            for (RolePermission permission : permissions) {
                Permission permission1 = permissionDao.find(permission.getPermissionId());
                result.add(permission1.getPermission());
            }
        }
        return result;
    }

    public boolean exists(Long roleId, Long permissionId) {
        List<RolePermission> permissions = rolePermissionDao.findByRole(roleId);
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }

        for (RolePermission permission : permissions) {
            if (permission.getPermissionId() == permissionId) {
                return true;
            }
        }
        return false;
    }
}
