package com.chethan.taskportal.service.impl;

import com.chethan.taskportal.Dto.request.LoginRequest;
import com.chethan.taskportal.Dto.request.RegisterRequest;
import com.chethan.taskportal.Dto.response.AuthResponse;
import com.chethan.taskportal.Util.JwtUtil;
import com.chethan.taskportal.entity.User;
import com.chethan.taskportal.globalException.UserAlreadyExistsException;
import com.chethan.taskportal.repository.UserRepository;
import com.chethan.taskportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // FIX: removed all System.out.println debug statements (exposed raw passwords)

        if (userRepository.existsByEmail(request.getEmail())) {
            // FIX: throw proper exception instead of returning silent empty response
            throw new UserAlreadyExistsException(
                    "Email already registered: " + request.getEmail()
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .message("User Registered Successfully")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        // FIX: throw exception so Spring returns HTTP 401, not HTTP 200
                        new RuntimeException("User not found")
                );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            // FIX: throw exception so Spring returns HTTP 401, not HTTP 200
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();
    }
}
