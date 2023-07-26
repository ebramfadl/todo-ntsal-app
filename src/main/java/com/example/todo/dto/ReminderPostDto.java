package com.example.todo.dto;

import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ReminderPostDto {

    @NotBlank
    @NotNull(message = "You need to provide the dueDate")
    private LocalDateTime dueDate;

    @NotBlank
    @NotNull(message = "You need to provide the task")
    private Long taskId;
}
