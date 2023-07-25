package com.example.todo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class SystemUser {

    @Id
    @SequenceGenerator(sequenceName = "user_sequence",allocationSize = 1,name = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "username", columnDefinition = "TEXT")
    private String username;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks ;

    @OneToMany(mappedBy = "user")
    private List<TodoList> categories ;

    public SystemUser(String username, String password) {
        this.username = username;
        this.password = password;
        tasks = new ArrayList<>();
        categories = new ArrayList<>();
    }
}
