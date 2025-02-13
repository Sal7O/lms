/*
* This controller is responsible for user's endpoints
*/

package com.edu.lms.controller;

import com.edu.lms.entity.User;
import com.edu.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> get_all_users() {
        return userService.get_all_users();
    }


    @GetMapping("/{id}")
    public User get_some_user(@PathVariable int id) {
        return userService.find_user(id);
    }


    @PostMapping("/")
    public void add_new_user(@RequestBody User user) {
        userService.add_new_user(user);
    }


    @PutMapping("/{id}")
    public void update_some_user(@RequestBody User user, @PathVariable int id) {
        userService.update_user(user, id);
    }

}
