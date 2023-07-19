package com.example.todo.model;


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
public class Group {

    @Id
    @SequenceGenerator(sequenceName = "group_sequence",allocationSize = 1,name = "group_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
    private Long id;

    private String title;
    private String description;

    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;

    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Invitation> invitations = new ArrayList<>();
}
