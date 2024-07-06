package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;

import java.util.List;


public interface CourseService {

    Course addCourse(Course course);

    List<CourseDto> findAllCourses();

    List<CourseDto> findCoursesBySemester(String semester);

    Course findCourseById(long id);
}
