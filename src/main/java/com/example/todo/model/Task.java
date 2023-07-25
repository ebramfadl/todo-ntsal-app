package com.example.todo.model;


import com.example.todo.enums.Priority;
import com.example.todo.enums.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tag tag;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_completed")
    private LocalDateTime dateCompleted;

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
    private TodoList todoList;

    public Task( Tag tag, String description, LocalDateTime deadline, Priority priority, SystemUser user, TodoList todoList) {
        this.tag = tag;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.user = user;
        this.todoList = todoList;
        status = TaskStatus.PENDING;
        dateCreated = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
    }
}
