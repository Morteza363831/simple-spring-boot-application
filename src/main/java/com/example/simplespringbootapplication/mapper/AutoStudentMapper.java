package com.example.simplespringbootapplication.mapper;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.modelmapper.internal.bytebuddy.build.Plugin;

@Mapper
public interface AutoStudentMapper {

    AutoStudentMapper studentMapper = Mappers.getMapper(AutoStudentMapper.class);


    // map Student to studentDto
    //@Mapping(target = "address",source = ".")
    StudentDto mapToStudentDto(Student student);


    // map studentDto to student
    Student mapToStudent(StudentDto studentDto);

    // this method work when u want to combine some fields in entity and show it in one field in dto
    /* default String getFullAddress(Student student) {
     * return student.getCity() + " " + student.getStreet() + " " + student.getPlaqueNumber();
    }*/
}
