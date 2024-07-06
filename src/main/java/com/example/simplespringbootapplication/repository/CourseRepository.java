package com.example.simplespringbootapplication.repository;

import com.example.simplespringbootapplication.dto.CourseDto;
import com.example.simplespringbootapplication.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

    List<Course> findAllBySemester(String semester);

    Course findById(long id);
}
