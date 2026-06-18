package com.chethan.taskportal.Dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;

    private Object data;
}