package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.service.StudentService;
import com.example.simplespringbootapplication.service.StudentServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentController(StudentService studentService, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.studentService = studentService;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @GetMapping("students/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long studentId) {
        StudentDto studentDto = studentService.findStudentById(studentId);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student student1 = this.studentService.addStudent(student);
        return new ResponseEntity<Student>(student1, HttpStatus.CREATED);
    }

    @GetMapping("students/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "2") Integer pageSize) {

        List<StudentDto> allStudents = this.studentService.findAllStudents(pageNumber,pageSize);
        return new ResponseEntity<List<StudentDto>>(allStudents, HttpStatus.OK);
    }

    @GetMapping("students/byEndName")
    public ResponseEntity<List<StudentDto>> getStudentsByEndName() {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameEndingWith("%a");
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }

    @GetMapping("students/byStartName")
    public ResponseEntity<List<StudentDto>> getStudentsByStartName() {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameStartingWith("m");
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }



}
