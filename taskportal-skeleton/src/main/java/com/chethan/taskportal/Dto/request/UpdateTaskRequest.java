package com.chethan.taskportal.Dto.request;

import com.chethan.taskportal.entity.Priority;
import com.chethan.taskportal.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Due Date is required")
    private LocalDate dueDate;        // ← ADDED
}
