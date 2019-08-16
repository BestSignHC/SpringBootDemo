package com.hecheng.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PermissionController {

    @RequiresPermissions(value = {"per1", "per2"}, logical = Logical.OR)
    @GetMapping("/per")
    public String adminAction() {
        return "per";
    }
}
