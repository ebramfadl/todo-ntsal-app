package com.example.todo.model;


import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reminder {

    @Id
    @SequenceGenerator(sequenceName = "reminder_sequence",allocationSize = 1,name = "reminder_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminder_sequence")
    private Long id;

    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;

    private LocalDateTime dueDate;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;

    @Enumerated(value = EnumType.STRING)
    private ReminderStatus status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Reminder(String title, String description, RepetitionType repetitionType, LocalDateTime dueDate, Task task) {
        this.title = title;
        this.description = description;
        this.repetitionType = repetitionType;
        this.dueDate = dueDate;
        this.task = task;
        dateCreated = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
        status = ReminderStatus.PENDING;
    }
}
