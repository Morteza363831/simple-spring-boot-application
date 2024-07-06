package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface StudentService {

    Student addStudent(StudentDto studentDto);

    Student updateStudent(Student student);

    StudentDto findStudentById(Long id);

    List<StudentDto> findAllStudents(int pageNumber, int pageSize);

    List<StudentDto> findStudentsByNameEndingWith(String endingWith);

    List<StudentDto> findStudentsByNameStartingWith(String name);
}
