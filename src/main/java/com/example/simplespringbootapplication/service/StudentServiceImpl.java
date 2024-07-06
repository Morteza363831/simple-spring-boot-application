package com.example.simplespringbootapplication.service;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.mapper.AutoStudentMapper;
import com.example.simplespringbootapplication.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    // inject student repo using constructor not autowired notation
    private final StudentRepository studentRepository;

    private ModelMapper modelMapper;
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    // add student to table
    @Override
    public Student addStudent(StudentDto addStudentDto) {
        Student addStudent = modelMapper.map(addStudentDto,Student.class);
        return studentRepository.save(addStudent);
    }


    //update student (add courses to it)
    @Override
    public Student updateStudent(Student updateStudent) {
        for (Course c : updateStudent.getCourses()) {
        }
        Student student = studentRepository.findById(updateStudent.getId()).get();
        student.setCourses(updateStudent.getCourses());
        return studentRepository.save(student);
    }


    //find student by id
    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }


    //find all students and make it pageable
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


    //find students where names end with
    @Override
    public List<StudentDto> findStudentsByNameEndsWith(String endsWith) {
        List<Student> studentList = studentRepository.findStudentByNameEndingWith(endsWith);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }


    // find students where names start with
    @Override
    public List<StudentDto> findStudentsByNameStartsWith(String startsWith) {
        List<Student> studentList = studentRepository.findStudentByNameStartingWith(startsWith);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }


}
