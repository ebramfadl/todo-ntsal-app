package com.example.todo.model;


import com.example.todo.enums.ReminderStatus;
import com.example.todo.enums.RepetitionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Reminder {

    @Id
    @SequenceGenerator(sequenceName = "reminder_sequence", allocationSize = 1, name = "reminder_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminder_sequence")
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToOne(mappedBy = "reminder")
    private Task task;

    public Reminder(LocalDateTime dueDate, Task task) {
        this.dueDate = dueDate;
        this.task = task;
        dateCreated = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
    }

    public String getDescription() {
        if (task == null)
            return "";
        return task.getDescription();
    }

    @Scheduled(cron = "0 * * * * ?")
    public String printNotification(){
        LocalDateTime now = LocalDateTime.now();
        if (dueDate.isBefore(now)) {
           return "Reminder: "+task.getDescription();
        }
        return "";
    }

}
