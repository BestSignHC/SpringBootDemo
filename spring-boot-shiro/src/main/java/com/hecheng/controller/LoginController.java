package com.hecheng.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginFormAction(HttpServletRequest request,
                                  @RequestParam(name = "kickOut", required = false) String kickOut) {
        if ("1".equals(kickOut)) {
            request.setAttribute("error", "您已在其他地方登陆！");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginAction(HttpServletRequest request,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(name = "rememberMe", required = false) Boolean rememberMe) {
        String error = null;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (null == rememberMe) {
            rememberMe = false;
        }
        token.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (LockedAccountException e) {
            error = "用户被锁定";
        } catch (Exception e) {
            error = "其他错误：" + e.getMessage();
        }
        if (null != error) {
            request.setAttribute("error", error);
            return "login";
        }
        else {
            return "success";
        }
    }
}
