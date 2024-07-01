package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> findAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }


}
