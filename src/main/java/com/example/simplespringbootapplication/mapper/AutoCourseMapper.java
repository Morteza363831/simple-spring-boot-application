package com.example.simplespringbootapplication.mapper;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoCourseMapper {

    AutoCourseMapper courseMapper = Mappers.getMapper(AutoCourseMapper.class);


    CourseDto mapToCourseDto(Course course);
}
