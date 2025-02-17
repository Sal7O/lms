/*
* This controller is for course management.
*/

package com.edu.lms.controller;

import com.edu.lms.entity.Course;
import com.edu.lms.service.CourseService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    List<Course> get_all_courses() {
        return this.courseService.get_all_courses();
    }

    @GetMapping("/{id}")
    Course get_some_course(@PathVariable int id) {
        return this.courseService.get_course(id);
    }

    @PostMapping
    void add_course(@RequestBody Course addedCourse) {
        this.courseService.add_course(addedCourse);
    }

    @PutMapping("/{id}")
    void update_course(@RequestBody Course course, @PathVariable int id) {
        this.courseService.update_course(course, id);
    }


}
