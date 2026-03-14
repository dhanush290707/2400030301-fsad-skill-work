package com.klu.controller;

import com.klu.model.Student;
import com.klu.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") String id) {
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
