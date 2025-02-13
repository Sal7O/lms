package com.edu.lms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @GetMapping("/hello-world")
    String hello_world() {
        return "Hello World";
    }

}
