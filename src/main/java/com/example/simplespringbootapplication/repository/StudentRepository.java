package com.example.simplespringbootapplication.repository;

import com.example.simplespringbootapplication.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
}
