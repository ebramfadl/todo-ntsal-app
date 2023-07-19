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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @SequenceGenerator(sequenceName = "task_sequence",allocationSize = 1,name = "task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;

    private String tag;
    private String description;

    private LocalDateTime deadline;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    private LocalDateTime dateCreated;
//    private LocalDateTime lastModifiedDate;

//    private boolean isFavorite;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @OneToMany(mappedBy = "task")
    private List<Reminder> reminders;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task( String tag, String description, LocalDateTime deadline, RepetitionType repetitionType, Priority priority, SystemUser user, Category category) {
        this.tag = tag;
        this.description = description;
        this.deadline = deadline;
        this.repetitionType = repetitionType;
        this.priority = priority;
        this.user = user;
        this.category = category;
        dateCreated = LocalDateTime.now();
        status = TaskStatus.PENDING;
        reminders = new ArrayList<>();
    }
}
