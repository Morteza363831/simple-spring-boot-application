package com.example.simplespringbootapplication;

import com.example.simplespringbootapplication.dto.StudentDto;
import com.example.simplespringbootapplication.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = "com.example")
public class SimpleSpringBootApplication {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(Student.class,StudentDto.class).addMappings(mapper -> {
            mapper.map(Student::getName, StudentDto::setName);
            mapper.map(Student::getId, StudentDto::setId);
            mapper.map(Student::getCity,StudentDto::setCity);
            mapper.map(Student::getStreet,StudentDto::setStreet);
            mapper.map(Student::getPlaqueNumber,StudentDto::setPlaqueNumber);
        });
        return modelMapper;
    }

    public static void main(String[] args) {

        SpringApplication.run(SimpleSpringBootApplication.class, args);


    }

}
