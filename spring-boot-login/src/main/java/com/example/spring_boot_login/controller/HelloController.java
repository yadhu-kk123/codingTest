package com.example.spring_boot_login.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Welcome to the Hello Page!";
    }
}
