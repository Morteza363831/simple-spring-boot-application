package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired private CourseService courseService;

    @PostMapping("courses")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("courses/all")
    public List<CourseDto> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("courses/all/{semester}")
    public List<CourseDto> findAllCoursesBySemester(@PathVariable String semester) {
        return courseService.findCoursesBySemester(semester);
    }
}
