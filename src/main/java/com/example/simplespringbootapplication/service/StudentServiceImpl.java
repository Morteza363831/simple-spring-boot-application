package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;

    private ModelMapper modelMapper;
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        studentDto.setAddress("city : " + student.getCity() + "- street : " + student.getStreet() + "- plaque : " + student.getPlaqueNumber());
        return studentDto;
    }

    @Override
    public List<StudentDto> findAllStudents(int pageNumber, int pageSize) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        List<StudentDto> studentDtoList = new ArrayList<>();
        if (studentPage.hasContent()) {
            for (Student student : studentPage.getContent()) {
                StudentDto studentDto = modelMapper.map(student, StudentDto.class);
                studentDto.setAddress("city : " + student.getCity() + "- street : " + student.getStreet() + "- plaque : " + student.getPlaqueNumber());
                studentDtoList.add(studentDto);
            }
        }
        return studentDtoList;
    }

    @Override
    public List<StudentDto> findStudentsByNameEndingWith(String endingWith) {
        List<Student> studentList = (List<Student>) studentRepository.findStudentByNameEndingWith(endingWith);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDto.setAddress("city : " + student.getCity() + "- street : " + student.getStreet() + "- plaque : " + student.getPlaqueNumber());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    @Override
    public List<StudentDto> findStudentsByNameStartingWith(String name) {
        List<Student> studentList = (List<Student>) studentRepository.findStudentByNameStartingWith(name);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDto.setAddress("city : " + student.getCity() + "- street : " + student.getStreet() + "- plaque : " + student.getPlaqueNumber());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }




}
