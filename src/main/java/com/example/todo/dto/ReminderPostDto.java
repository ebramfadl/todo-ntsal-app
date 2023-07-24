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

    private LocalDateTime dueDate;
    private Long taskId;
}
