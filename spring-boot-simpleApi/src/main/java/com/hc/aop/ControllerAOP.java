//package com.hc.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
////@Configuration
//public class ControllerAOP {
//
//    @Pointcut("execution(public * com.hc.controller.*.*(..))")
//    public void webLog(){
//    }
//
////    @Before("webLog()")
////    public void doBefore() {
////        System.out.println("Before Request......");
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
////        System.out.println("URL : " + request.getRequestURL().toString());
////    }
//
////    @AfterReturning(returning = "ret", pointcut = "webLog()")
////    public void doAfterReturning(Object ret) throws Throwable {
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        System.out.println("Response : " + ret);
////        HttpServletResponse response = attributes.getResponse();
////        if (ret instanceof String) {
////            String retStr = (String) ret;
////            retStr = retStr + (" timeStamp :" + System.currentTimeMillis());
////            response.getOutputStream().write(retStr.getBytes());
////            response.getOutputStream().flush();
////            return;
////        }
////    }
//
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        Long start = System.currentTimeMillis();
//        try {
//            Object o =  pjp.proceed();
//            System.out.println("Around proceed，结果是 :" + o);
//            Long cost = System.currentTimeMillis() - start;
//            if (o instanceof String) {
//                String res = (String) o;
//                res += ("cost: " + cost);
//                return res;
//            }
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
