package com.example.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "category")
public class Category {
    @Id
    @SequenceGenerator(sequenceName = "category_sequence",allocationSize = 1,name = "category_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    private Long id;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;


    public Category(String title, String description, SystemUser user) {
        this.title = title;
        this.description = description;
        dateCreated = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
        tasks = new ArrayList<>();
        this.user = user;
    }
}
