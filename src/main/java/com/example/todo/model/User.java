package com.example.todo.model;


import com.example.todo.enums.UserRole;
import com.example.todo.enums.UserStatus;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;
    private String email;
    private String name;

    private LocalDateTime registerationDate;
    private LocalDateTime lastModifiedDate;

    private String address;
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Issue> issues = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Invitation> invitations = new ArrayList<>();

}
