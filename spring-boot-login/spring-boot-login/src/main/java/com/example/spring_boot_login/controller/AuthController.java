package com.example.spring_boot_login.controller;

import com.example.spring_boot_login.dto.LoginRequest;
import com.example.spring_boot_login.dto.LoginResponse;
import com.example.spring_boot_login.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")  // Add this annotation
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String userId, @RequestParam String password) {
        return userService.registerUser(userId, password);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
}