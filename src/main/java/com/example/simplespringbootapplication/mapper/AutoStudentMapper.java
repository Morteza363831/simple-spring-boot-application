package com.example.simplespringbootapplication.mapper;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.modelmapper.internal.bytebuddy.build.Plugin;

@Mapper
public interface AutoStudentMapper {

    AutoStudentMapper MAPPER = Mappers.getMapper(AutoStudentMapper.class);


    @Mapping(target = "address",source = ".")
    StudentDto mapToStudentDto(Student student);


    default String getFullAddress(Student student) {
        return student.getCity() + " " + student.getStreet() + " " + student.getPlaqueNumber();
    }
}
