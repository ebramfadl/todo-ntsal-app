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
@NoArgsConstructor
@Data
public class SystemUser {

    @Id
    @SequenceGenerator(sequenceName = "user_sequence",allocationSize = 1,name = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks ;

    @OneToMany(mappedBy = "user")
    private List<Category> categories ;

    public SystemUser(String username, String password) {
        this.username = username;
        this.password = password;
        tasks = new ArrayList<>();
        categories = new ArrayList<>();
    }
}
