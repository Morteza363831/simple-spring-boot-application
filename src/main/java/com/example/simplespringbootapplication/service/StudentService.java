package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.entity.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);

    Student findStudentById(Long id);

    List<Student> findAllStudents();
}
