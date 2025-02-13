/*
* This controller is responsible for student's endpoints
*/

package com.edu.lms.controller;

import com.edu.lms.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    // Get all students in system
    @GetMapping
    String get_all_students() {
        return null;
    }

    // Add a new student
    @PostMapping
    void add_new_student(@RequestBody User user) {

    }

    // Get some student
    @GetMapping("/{id}")
    String get_student(@PathVariable int id) {
        return null;
    }

    // Modify some student
    @PutMapping("/{id}")
    void modify_student(@RequestBody User user, @PathVariable int id) {

    }
}
