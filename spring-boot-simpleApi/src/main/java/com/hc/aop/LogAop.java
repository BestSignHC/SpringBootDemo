package com.hc.aop;

import com.hc.annotation.ArroundLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class LogAop {
    @Pointcut("@annotation(com.hc.annotation.ArroundLog)")
    public void log() {
    }

    @Around("log()")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
        Object object = null;
        long start = System.currentTimeMillis();

        Method method = ((MethodSignature) MethodSignature.class.cast(point.getSignature())).getMethod();
        ArroundLog annotation = method.getAnnotation(ArroundLog.class);

        String operationName = annotation.value();
        object = point.proceed(point.getArgs());
        long end = System.currentTimeMillis();
        Long excuteTime = end - start;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println(request.getRequestURI());
        System.out.println(point.getArgs());
        System.out.println(operationName);
        System.out.println(excuteTime);
        return object;
    }
}

