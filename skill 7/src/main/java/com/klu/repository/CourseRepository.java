package com.klu.repository;

import com.klu.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    
    private final List<Course> courses = new ArrayList<>();

    public Course save(Course course) {
        courses.add(course);
        return course;
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getCourseId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return courses.removeIf(course -> course.getCourseId().equals(id));
    }

    public List<Course> findByTitleContainingIgnoreCase(String title) {
        return courses.stream()
                .filter(course -> course.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
