package com.example.todo.model;


import com.example.todo.enums.Priority;
import com.example.todo.enums.RepetitionType;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * this model contains
 */
@Entity
@NoArgsConstructor
@Data
public class Task {

    @Id
    @SequenceGenerator(sequenceName = "task_sequence",allocationSize = 1,name = "task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;
    private String tag;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @OneToOne
    @JoinColumn(name = "reminder_id")
    private Reminder reminder;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task( String tag, String description, LocalDateTime deadline, Priority priority, SystemUser user, Category category) {
        this.tag = tag;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.user = user;
        this.category = category;
        status = TaskStatus.PENDING;
        dateCreated = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
    }
}
