package com.klu.repository;

import com.klu.model.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepository {

    private final Map<String, Student> studentDatabase = new HashMap<>();

    public StudentRepository() {
        studentDatabase.put("1", new Student("1", "Alice Smith", "Computer Science"));
        studentDatabase.put("2", new Student("2", "Bob Jones", "Mathematics"));
        studentDatabase.put("3", new Student("3", "Charlie Brown", "Physics"));
    }

    public boolean existsById(String id) {
        return studentDatabase.containsKey(id);
    }

    public Student findById(String id) {
        return studentDatabase.get(id);
    }
}
