package com.example.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    @Id
    @SequenceGenerator(sequenceName = "notification_sequence",allocationSize = 1,name = "notification_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence")
    private Long id;
}
