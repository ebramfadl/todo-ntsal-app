package com.example.todo.dto;

import com.example.todo.enums.Priority;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TaskPostDto {

    @NotBlank
    private Long tagId;

    @NotBlank
    @NotNull(message = "You need to provide a description")
    private String description;

    @NotBlank
    @NotNull(message = "You need to provide the deadline")
    private LocalDateTime deadline;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Long categoryId;

    @NotNull(message = "You need to provide the userId")
    private Long userId;
}
