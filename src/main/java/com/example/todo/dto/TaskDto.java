package com.example.todo.dto;


import com.example.todo.enums.DegreeOfImportance;
import com.example.todo.enums.RepetitionType;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TaskDto {

    private String tag;
    private String description;
    private LocalDateTime deadline;
    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;
    @Enumerated(value = EnumType.STRING)
    private DegreeOfImportance degreeOfImportance;
    private Long userId;
    private Long categoryName;
}
