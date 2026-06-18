package com.chethan.taskportal.Mapper;

import com.chethan.taskportal.Dto.response.TaskResponse;
import com.chethan.taskportal.entity.Task;

public class TaskMapper {

    public static TaskResponse mapToResponse(Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())    // ← ADDED
                .build();
    }
}
