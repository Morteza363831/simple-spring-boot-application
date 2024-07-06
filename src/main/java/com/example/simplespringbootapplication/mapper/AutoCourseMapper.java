package com.example.simplespringbootapplication.mapper;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoCourseMapper {

    AutoCourseMapper courseMapper = Mappers.getMapper(AutoCourseMapper.class);


    // map course to courseDto
    CourseDto mapToCourseDto(Course course);

    // map courseDto to course
    Course mapToCourse(CourseDto courseDto);
}
