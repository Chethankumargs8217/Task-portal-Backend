package com.chethan.taskportal.controller;

import com.chethan.taskportal.Dto.request.LoginRequest;
import com.chethan.taskportal.Dto.request.RegisterRequest;
import com.chethan.taskportal.Dto.response.AuthResponse;
import com.chethan.taskportal.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}