package com.chethan.taskportal.service;

import com.chethan.taskportal.Dto.request.LoginRequest;
import com.chethan.taskportal.Dto.request.RegisterRequest;
import com.chethan.taskportal.Dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}