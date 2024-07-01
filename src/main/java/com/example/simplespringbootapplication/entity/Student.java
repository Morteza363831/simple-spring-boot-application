package com.example.simplespringbootapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    private long id;

    private String name;

    private String password;
}
