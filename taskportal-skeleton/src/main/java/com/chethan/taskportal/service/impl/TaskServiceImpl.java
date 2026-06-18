package com.chethan.taskportal.service.impl;

import com.chethan.taskportal.Dto.request.CreateTaskRequest;
import com.chethan.taskportal.Dto.request.UpdateTaskRequest;
import com.chethan.taskportal.Dto.response.TaskResponse;
import com.chethan.taskportal.Mapper.TaskMapper;
import com.chethan.taskportal.entity.Task;
import com.chethan.taskportal.entity.TaskStatus;
import com.chethan.taskportal.entity.User;
import com.chethan.taskportal.globalException.ResourceNotFoundException;
import com.chethan.taskportal.globalException.UnauthorizedException;
import com.chethan.taskportal.repository.TaskRepository;
import com.chethan.taskportal.repository.UserRepository;
import com.chethan.taskportal.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;    // ← ADDED

    // ── Helper: get the currently authenticated user ──────────────────────────
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Authenticated user not found: " + email));
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        User currentUser = getCurrentUser();             // ← ADDED

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());           // ← ADDED
        task.setCreatedAt(LocalDateTime.now());          // ← ADDED
        task.setStatus(TaskStatus.TODO);
        task.setUser(currentUser);                       // ← ADDED

        Task savedTask = taskRepository.save(task);
        return TaskMapper.mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        User currentUser = getCurrentUser();             // ← ADDED

        return taskRepository.findByUserId(currentUser.getId())   // ← filter by user
                .stream()
                .map(TaskMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {

        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found with id: " + id));

        // Security check — user can only edit their own tasks
        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException(
                    "You are not authorized to update this task");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());           // ← ADDED

        Task updatedTask = taskRepository.save(task);
        return TaskMapper.mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found with id: " + id));

        // Security check — user can only delete their own tasks
        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException(
                    "You are not authorized to delete this task");
        }

        taskRepository.delete(task);
    }
}
