package com.hecheng.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String adminAction() {
        return "admin";
    }
}
