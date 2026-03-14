package com.klu.service;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;
import com.klu.exception.InvalidInputException;
import com.klu.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id) {
        try {
            int studentId = Integer.parseInt(id);
            if (studentId <= 0) {
                throw new InvalidInputException("Student ID must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID format. Student ID must be numeric.");
        }

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " was not found in the system.");
        }

        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        if (student == null || student.getId() == null) {
            throw new InvalidInputException("Student details cannot be null.");
        }

        try {
            int studentId = Integer.parseInt(student.getId());
            if (studentId <= 0) {
                throw new InvalidInputException("Student ID must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID format. Student ID must be numeric.");
        }

        if (studentRepository.existsById(student.getId())) {
            throw new InvalidInputException("Student with ID " + student.getId() + " already exists.");
        }

        return studentRepository.save(student);
    }
}
