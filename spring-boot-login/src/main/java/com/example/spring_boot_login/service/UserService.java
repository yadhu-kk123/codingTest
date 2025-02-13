package com.example.spring_boot_login.service;

import com.example.spring_boot_login.config.JwtUtil;
import com.example.spring_boot_login.dto.LoginRequest;
import com.example.spring_boot_login.dto.LoginResponse;
import com.example.spring_boot_login.entity.User;
import com.example.spring_boot_login.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);
        return "User registered successfully";
    }

    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return new LoginResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}