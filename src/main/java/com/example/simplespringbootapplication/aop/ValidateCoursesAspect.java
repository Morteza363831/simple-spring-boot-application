package com.example.simplespringbootapplication.aop;

import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.service.CourseService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class ValidateCoursesAspect {

    private final CourseService courseService;

    public ValidateCoursesAspect(CourseService courseService) {
        this.courseService = courseService;
    }

    @Around("@annotation(com.example.simplespringbootapplication.aop.ValidateCourses)")
    public Object validateCourses(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("inside validateCourses");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof String) {
                List<String> coursesName = Arrays.stream(((String) arg).split(",")).toList();
                for (String courseName : coursesName) {
                    System.out.println("here");
                    validateCourseNames(courseName);
                }
            }
        }
        return joinPoint.proceed();
    }

    private void validateCourseNames(String courseName) {
        if (!courseService.isCourseNameValid(courseName)) {
            System.out.println("invalid validation method");
            throw new IllegalArgumentException("Invalid course name: " + courseName);
        }
    }




}
