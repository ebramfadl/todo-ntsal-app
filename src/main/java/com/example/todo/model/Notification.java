package com.example.todo.model;


import com.example.todo.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    @Id
    @SequenceGenerator(sequenceName = "notification_sequence",allocationSize = 1,name = "notification_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence")
    private Long id;
    private String title;
    private String content;
    //method
    private NotificationStatus status;
    private LocalDateTime dateSent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
