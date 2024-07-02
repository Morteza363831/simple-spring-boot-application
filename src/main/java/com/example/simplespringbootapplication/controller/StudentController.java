package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.service.StudentService;
import com.example.simplespringbootapplication.service.StudentServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> studentDtos = this.studentService.findAllStudents();
        return new ResponseEntity<List<StudentDto>>(studentDtos, HttpStatus.OK);
    }

    @GetMapping("students/byEndName")
    public ResponseEntity<List<StudentDto>> getStudentsByEndName() {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameEndingWith();
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }



}
