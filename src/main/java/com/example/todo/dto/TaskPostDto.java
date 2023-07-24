package com.example.todo.dto;

import com.example.todo.enums.Priority;
import com.example.todo.enums.RepetitionType;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TaskPostDto {

    private String tag;
    private String description;
    private LocalDateTime deadline;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Long categoryId;
    private Long userId;
}
