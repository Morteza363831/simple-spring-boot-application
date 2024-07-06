package com.example.simplespringbootapplication.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CourseAspectService {

    @Before(value = "execution(* com.example.simplespringbootapplication.controller.CourseController.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getArgs() + " args");
        System.out.println(joinPoint.getStaticPart() + " static part");
        System.out.println(joinPoint.getKind() + " kind");
        System.out.println(joinPoint.getSignature() + " signature");
        System.out.println("before course service");
    }
/*
    @After(value = "execution(* com.example.simplespringbootapplication..*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("after course service");
    }

    @AfterReturning(value = "execution(* com.example.simplespringbootapplication..*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("after (returning) course service");
    }

    @Around(value = "execution(* com.example.simplespringbootapplication..*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around course service");
    }*/
}
