package com.workintech.e_commerce.controller;

import com.workintech.e_commerce.dto.UserRequestDto;
import com.workintech.e_commerce.dto.UserResponseDto;
import com.workintech.e_commerce.entity.User;
import com.workintech.e_commerce.service.CustomUserDetailsService;
import com.workintech.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = (User) userDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(password, user.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            response.put("fullName", user.getFullName());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Validated @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.create(userRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
