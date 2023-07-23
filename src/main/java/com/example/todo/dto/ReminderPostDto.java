package com.example.todo.dto;

import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ReminderPostDto {

    private String title;
    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;
    private LocalDateTime dueDate;
    @Enumerated(EnumType.STRING)
    private ReminderStatus status;
    private Long taskId;
}
