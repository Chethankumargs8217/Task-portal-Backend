package com.chethan.taskportal.Dto.response;

import com.chethan.taskportal.entity.Priority;
import com.chethan.taskportal.entity.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private LocalDate dueDate;

    private TaskStatus status;

    private LocalDateTime createdAt;   // ← ADDED
}
