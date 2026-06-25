package com.example.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> theStudents;
    
    @PostConstruct
    public void loadData() {
        theStudents = List.of(
            new Student("John", "Doe"),
            new Student("Jane", "Smith"),
            new Student("Mike", "Johnson")
        );
    }

    @GetMapping("/students")
    public List<Student> getTheStudents() {
        return theStudents;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if(studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }


    // add an exception handler using @ExceptionHandler
       
}
