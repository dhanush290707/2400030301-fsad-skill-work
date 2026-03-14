package com.klu.controller;

import com.klu.exception.CourseNotFoundException;
import com.klu.model.Course;
import com.klu.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        Course created = courseService.addCourse(course);
        return new ResponseEntity<>("Course " + created.getTitle() + " created successfully.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        courseService.updateCourse(id, courseDetails);
        return new ResponseEntity<>("Course updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourses(@PathVariable String title) {
        List<Course> foundCourses = courseService.searchCoursesByTitle(title);
        return new ResponseEntity<>(foundCourses, HttpStatus.OK);
    }
}
