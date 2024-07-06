package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Course;
import com.example.simplespringbootapplication.entity.Student;
import com.example.simplespringbootapplication.mapper.AutoStudentMapper;
import com.example.simplespringbootapplication.service.CourseService;
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
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentController(StudentService studentService, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CourseService courseService) {
        this.studentService = studentService;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.courseService = courseService;
    }


    @GetMapping("students/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long studentId) {
        StudentDto studentDto = studentService.findStudentById(studentId);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }


    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto addStudentDto) {
        Student student = this.studentService.addStudent(addStudentDto);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @PostMapping("students/{studentId}/{coursesName}")
    public ResponseEntity<Student> addCoursesToStudent(@PathVariable long studentId,
                                                       @PathVariable String coursesName) {
        List<String> coursesNamesList = Arrays.stream(coursesName.split(",")).toList();
        StudentDto studentDto = studentService.findStudentById(studentId);
        Student student = AutoStudentMapper.studentMapper.mapToStudent(studentDto);
        List<Course> courses = new ArrayList<>();
        for (String courseName : coursesNamesList) {
            Course course = new Course();
            course.setCourseName(courseName);
            course.setSemester("spring");
            course.setStudent(student);
            courseService.addCourse(course);
            courses.add(course);
        }
        student.setCourses(courses);
        return new ResponseEntity<Student>(studentService.updateStudent(student),HttpStatus.OK);
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


    @GetMapping("students/byStartName/{startingWith}")
    public ResponseEntity<List<StudentDto>> getStudentsByStartName(@PathVariable String startingWith) {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameStartingWith(startingWith);
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }



}
