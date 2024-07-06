package com.example.simplespringbootapplication.dto;

import lombok.Data;


// writer dto
@Data
public class StudentDto {
    long id;
    long courseId;
    String name;
    String password;
    String City;
    String street;
    String plaqueNumber;


}
