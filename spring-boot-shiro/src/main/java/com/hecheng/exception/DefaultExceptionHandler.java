package com.hecheng.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(UnauthorizedException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
        return "unauth";
    }

    @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(HttpStatus.OK)
    public String processAuthorizationException(AuthorizationException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
        return "login";
    }
}
