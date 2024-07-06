package com.example.simplespringbootapplication.repository;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student,Long>, CrudRepository<Student,Long> {

    @Query(value = "SELECT * FROM student s WHERE s.name like ?1", nativeQuery = true)
    List<Student> findStudentByNameEndingWith(String endingWith);

    List<Student> findStudentByNameStartingWith(String name);
}
