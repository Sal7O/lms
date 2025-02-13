/*
 * This controller is responsible for instructor's endpoints
 */

package com.edu.lms.controller;

import com.edu.lms.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    // Get all instructors in system
    @GetMapping
    String get_all_instructors() {
        return null;
    }

    // Add a new instructor
    @PostMapping
    void add_new_instructor(@RequestBody User user) {

    }

    // Get some instructor
    @GetMapping("/{id}")
    String get_instructor(@PathVariable int id) {
        return null;
    }

    // Modify some instructor
    @PutMapping("/{id}")
    void modify_instructor(@RequestBody User user, @PathVariable int id) {

    }
}
