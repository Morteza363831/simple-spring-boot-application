package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    // inject course service
    @Autowired private CourseService courseService;

    // add a course to table
    @PostMapping("courses")
    public Course addCourse(@RequestBody CourseDto addCourseDto) {
        return courseService.addCourse(addCourseDto);
    }

    // get all courses from db
    @GetMapping("courses/all")
    public List<CourseDto> findAllCourses() {
        return courseService.findAllCourses();
    }

    // get all courses from db with special semester
    @GetMapping("courses/all/{semester}")
    public List<CourseDto> findAllCoursesBySemester(@PathVariable String semester) {
        return courseService.findCoursesBySemester(semester);
    }
}
