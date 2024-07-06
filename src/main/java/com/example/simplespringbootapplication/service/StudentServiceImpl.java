package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.mapper.AutoStudentMapper;
import com.example.simplespringbootapplication.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
    public Student addStudent(StudentDto addStudentDto) {
        Student addStudent = modelMapper.map(addStudentDto,Student.class);
        return studentRepository.save(addStudent);
    }


    @Override
    public Student updateStudent(Student updateStudent) {
        for (Course c : updateStudent.getCourses()) {
        }
        Student student = studentRepository.findById(updateStudent.getId()).get();
        student.setCourses(updateStudent.getCourses());
        return studentRepository.save(student);
    }


    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
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
                StudentDto studentDto = AutoStudentMapper.studentMapper.mapToStudentDto(student);
                //StudentDto studentDto = modelMapper.map(student, StudentDto.class);
                studentDtoList.add(studentDto);
            }
        }
        return studentDtoList;
    }


    @Override
    public List<StudentDto> findStudentsByNameEndingWith(String endingWith) {
        List<Student> studentList = studentRepository.findStudentByNameEndingWith(endingWith);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }


    @Override
    public List<StudentDto> findStudentsByNameStartingWith(String name) {
        List<Student> studentList = studentRepository.findStudentByNameStartingWith(name);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }


}
