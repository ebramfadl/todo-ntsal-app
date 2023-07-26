package com.example.todo.dto;


import com.example.todo.enums.Priority;
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
public class TaskDto {

    private String tagName;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
    @Enumerated(value = EnumType.STRING)
    private Priority priority;
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    private String categoryTitle;
}
