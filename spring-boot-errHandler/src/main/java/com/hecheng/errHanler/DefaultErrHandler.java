package com.hecheng.errHanler;

import com.alibaba.fastjson.JSONObject;
import com.hecheng.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultErrHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject defaultErrHandler(Exception e) {
        JSONObject result = new JSONObject();

        result.put("code", -1);
        result.put("error message", e.getMessage());

        return result;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public JSONObject myExceptionErrHandler(MyException e) {
        JSONObject result = new JSONObject();

        result.put("code", e.getCode());
        result.put("error message", e.getMessage());

        return result;
    }

}
