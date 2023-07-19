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
public class Team {

    @Id
    @SequenceGenerator(sequenceName = "team_sequence",allocationSize = 1,name = "team_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    private Long id;

    private String title;
    private String description;

    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;

    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<SystemUser> users = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Invitation> invitations = new ArrayList<>();
}
