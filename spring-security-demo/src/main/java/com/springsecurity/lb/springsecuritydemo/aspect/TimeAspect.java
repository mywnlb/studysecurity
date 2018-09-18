package com.springsecurity.lb.springsecuritydemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//@Aspect
public class TimeAspect {
    @Around("execution(* com.springsecurity.lb.springsecuritydemo.controller.* .*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for(Object arg :args){
            System.out.println(arg);
        }

        long stat = new Date().getTime();
        Object object = pjp.proceed();

        System.out.println(new Date().getTime()-stat);
        System.out.println("time asoect end");
        return object;
    }
}
