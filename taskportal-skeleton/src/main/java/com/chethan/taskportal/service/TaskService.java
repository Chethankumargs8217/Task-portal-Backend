package com.chethan.taskportal.service;

import com.chethan.taskportal.Dto.request.CreateTaskRequest;
import com.chethan.taskportal.Dto.request.UpdateTaskRequest;
import com.chethan.taskportal.Dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(
            Long id,
            UpdateTaskRequest request
    );

    void deleteTask(Long id);
}