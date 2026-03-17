package com.klu.controller;

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
        if (course.getCourseId() == null || course.getTitle() == null) {
            return new ResponseEntity<>("Error: Missing required fields", HttpStatus.BAD_REQUEST);
        }
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
            return new ResponseEntity<>("Error: Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        if (courseDetails.getCourseId() != null && !id.equals(courseDetails.getCourseId())) {
            return new ResponseEntity<>("Error: Path mismatch with Course ID", HttpStatus.BAD_REQUEST);
        }
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        if (updatedCourse != null) {
            return new ResponseEntity<>("Course updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourses(@PathVariable String title) {
        List<Course> foundCourses = courseService.searchCoursesByTitle(title);
        if (foundCourses.isEmpty()) {
            return new ResponseEntity<>("Error: No courses found with title: " + title, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundCourses, HttpStatus.OK);
    }
}
