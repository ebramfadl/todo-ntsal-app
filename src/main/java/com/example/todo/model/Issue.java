package com.example.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {

    @Id
    @SequenceGenerator(sequenceName = "issue_sequence",allocationSize = 1,name = "issue_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_sequence")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;
}
