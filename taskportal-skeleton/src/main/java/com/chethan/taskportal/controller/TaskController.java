package com.chethan.taskportal.controller;

import com.chethan.taskportal.Dto.request.CreateTaskRequest;
import com.chethan.taskportal.Dto.request.UpdateTaskRequest;
import com.chethan.taskportal.Dto.response.TaskResponse;
import com.chethan.taskportal.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(
            @Valid @RequestBody CreateTaskRequest request){

        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(){

        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request){

        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(
            @PathVariable Long id){

        taskService.deleteTask(id);

        return "Task Deleted Successfully";
    }
}