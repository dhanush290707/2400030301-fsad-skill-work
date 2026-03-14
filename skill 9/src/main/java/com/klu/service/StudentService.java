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
        // Task 8: Test invalid input format (e.g., text instead of number) to trigger
        // InvalidInputException
        try {
            int studentId = Integer.parseInt(id);
            if (studentId <= 0) {
                throw new InvalidInputException("Student ID must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID format. Student ID must be numeric.");
        }

        // Trigger StudentNotFoundException if ID doesn't exist
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " was not found in the system.");
        }

        return studentRepository.findById(id);
    }
}
