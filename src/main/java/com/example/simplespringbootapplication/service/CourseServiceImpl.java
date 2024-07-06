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

    // inject course repo
    @Autowired private CourseRepository courseRepository;


    // add course to table
    @Override
    public Course addCourse(CourseDto addCourseDto) {
        Course course = AutoCourseMapper.courseMapper.mapToCourse(addCourseDto);
        return courseRepository.save(course);
    }


    // find all courses
    @Override
    public List<CourseDto> findAllCourses() {
        // find courses
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        // map it to course dto to show it --> here we used writer dto ! u can use reader dto !!!
        List<CourseDto> courseDtoList = new ArrayList<>();
        // map all courses
        for(Course course : courseList) {
            CourseDto courseDto = AutoCourseMapper.courseMapper.mapToCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }


    // find courses by semester
    @Override
    public List<CourseDto> findCoursesBySemester(String semester) {
        // like previous method
        List<Course> courseList = courseRepository.findAllBySemester(semester);
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courseList) {
            CourseDto courseDto = AutoCourseMapper.courseMapper.mapToCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }


    // find courses by id
    @Override
    public Course findCourseById(long id) {
        return courseRepository.findById(id);
    }


    public boolean isCourseNameValid(String courseName) {
        if (courseName.contains("a")) {
            return true;
        }
        return false;
    }
}
