package com.example.simplespringbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* it doesent make a new column in student table ! when u call a specific student with id 1 ,
    * it will join student and course where student id is 1 then it store course objects in the
    * courses list here !*/
    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private List<Course> courses = new ArrayList<>();

    private String name;

    private String password;

    private String city;

    private String street;

    private String plaqueNumber;
}
