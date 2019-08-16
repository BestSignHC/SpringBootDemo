package com.hecheng.controller;

import com.hecheng.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Hello Spring-Boot~";
    }

    @RequestMapping("/err")
    public void doException() throws Exception {
        throw new Exception("err occur");
    }

    @RequestMapping("/err2")
    public void doException2() throws MyException {
        throw new MyException(-2, "rr occur, code: -2");
    }
}
