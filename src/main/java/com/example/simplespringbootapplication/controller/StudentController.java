package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.service.StudentService;
import com.example.simplespringbootapplication.service.StudentServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("welcome")
    public String welcome() {
        Student student = new Student();
        student.setId(1L);
        student.setName("morteza");
        student.setPassword("363831");
        studentService.addStudent(student);

        Student result = studentService.findStudentById(1L);

        return result.getName() + " " + result.getPassword();
    }

    @GetMapping("students/{studentId}")
    public Student getStudent(@PathVariable long studentId) {
        return studentService.findStudentById(studentId);
    }

    @PostMapping("students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }



}
