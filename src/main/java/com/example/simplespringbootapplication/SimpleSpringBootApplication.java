package com.example.simplespringbootapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.example")
public class SimpleSpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(SimpleSpringBootApplication.class, args);

    }

}
