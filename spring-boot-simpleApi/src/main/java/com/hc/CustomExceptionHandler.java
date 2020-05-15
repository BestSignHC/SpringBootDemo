package com.hc;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public @ResponseBody HashMap<String, Object> handle(InvalidFormatException e) {
        e.printStackTrace();
        final List<String> collect = e
                .getPath()
                .stream()
                .map(it -> it.getFieldName() + " format error.")
                .collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("message", collect);
        }};
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody String handle2(MethodArgumentNotValidException e){
        e.printStackTrace();
        return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
