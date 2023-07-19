package com.example.todo.dto;

import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import com.example.todo.model.Task;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ReminderDto {
    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;
    private LocalDateTime dueDate;
    private LocalDateTime dateCreated;
    @Enumerated(value = EnumType.STRING)
    private ReminderStatus status;
}
