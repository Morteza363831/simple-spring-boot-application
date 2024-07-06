package com.example.simplespringbootapplication.dto;

import com.example.simplespringbootapplication.entity.Student;
import lombok.Data;

@Data
public class CourseDto {

    long id;
    String courseName;
    String semester;
}
