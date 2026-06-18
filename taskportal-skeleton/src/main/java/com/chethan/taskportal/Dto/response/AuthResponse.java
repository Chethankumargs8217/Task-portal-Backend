package com.chethan.taskportal.Dto.response;

import com.chethan.taskportal.Dto.request.RegisterRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    private String message;

}