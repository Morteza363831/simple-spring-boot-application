package com.example.simplespringbootapplication.controller;

import com.example.simplespringbootapplication.aop.ValidateCourses;
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

    // inject dependencies
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService,
                             CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    // get student by id
    @GetMapping("students/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long studentId) {
        StudentDto studentDto = studentService.findStudentById(studentId);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }


    // add student
    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto addStudentDto) {
        Student student = this.studentService.addStudent(addStudentDto);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    /* update student (add courses to student) here we will call the add course method to add new
    * courses ! */
    @PostMapping("students/{studentId}/{coursesName}")
    @ValidateCourses
    public ResponseEntity<Student> addCoursesToStudent(@PathVariable long studentId,
                                                       @PathVariable String coursesName) {
        // list of courses
        List<String> coursesNamesList = Arrays.stream(coursesName.split(",")).toList();
        StudentDto studentDto = studentService.findStudentById(studentId);
        Student student = AutoStudentMapper.studentMapper.mapToStudent(studentDto);
        // this list is for student with studentId
        List<Course> courses = new ArrayList<>();
        // add courses to course table
        for (String courseName : coursesNamesList) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(courseName);
            courseDto.setSemester("spring");
            // map courseDto to course
            Course course = courseService.addCourse(courseDto);
            // add student id to course table --> refference to student table
            course.setStudent(student);
            courses.add(course);
        }
        // set student courses
        student.setCourses(courses);
        return new ResponseEntity<Student>(studentService.updateStudent(student),HttpStatus.OK);
    }


    // get all students from db and make it pageable !!
    @GetMapping("students/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "2") Integer pageSize) {

        List<StudentDto> allStudents = this.studentService.findAllStudents(pageNumber,pageSize);
        return new ResponseEntity<List<StudentDto>>(allStudents, HttpStatus.OK);
    }


    // get students which names end with namesEndWith
    @GetMapping("students/namesEndWith")
    public ResponseEntity<List<StudentDto>> getStudentsByEndName() {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameEndsWith("%a");
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }


    // get students which names start with namesStartWith
    @GetMapping("students/byStartName/{namesStartingWith}")
    public ResponseEntity<List<StudentDto>> getStudentsByStartName(@PathVariable String namesStartingWith) {
        List<StudentDto> studentDtos = this.studentService.findStudentsByNameStartsWith(namesStartingWith);
        return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
    }



}
