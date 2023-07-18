package com.example.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {

    @Id
    @SequenceGenerator(sequenceName = "group_sequence",allocationSize = 1,name = "group_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
    private Long id;

    private String title;
    private String description;

    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}
