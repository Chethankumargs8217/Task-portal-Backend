package com.chethan.taskportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}