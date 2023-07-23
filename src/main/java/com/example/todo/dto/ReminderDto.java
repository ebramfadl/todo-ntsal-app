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
import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class ReminderDto {
    private String title;
    private LocalDateTime dueDate;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;

    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;

    @Enumerated(value = EnumType.STRING)
    private ReminderStatus status;
}
