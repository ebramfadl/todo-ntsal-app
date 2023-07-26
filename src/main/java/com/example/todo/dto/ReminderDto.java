package com.example.todo.dto;

import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class ReminderDto {

    @NotBlank
    @NotNull(message = "You need to provide a description")
    private String description;

    @NotBlank
    @NotNull(message = "You need to provide the dueDate")
    private LocalDateTime dueDate;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}
