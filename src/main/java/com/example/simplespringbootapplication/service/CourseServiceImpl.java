package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.mapper.AutoCourseMapper;
import com.example.simplespringbootapplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired private CourseRepository courseRepository;


    @Override
    public Course addCourse(Course addCourse) {
        Course course = courseRepository.save(addCourse);
        return course;
    }


    @Override
    public List<CourseDto> findAllCourses() {
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courseList) {
            CourseDto courseDto = AutoCourseMapper.courseMapper.mapToCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }


    @Override
    public List<CourseDto> findCoursesBySemester(String semester) {
        List<Course> courseList = courseRepository.findAllBySemester(semester);
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courseList) {
            CourseDto courseDto = AutoCourseMapper.courseMapper.mapToCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }


    @Override
    public Course findCourseById(long id) {
        return courseRepository.findById(id);
    }
}
