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
public class SystemUser {

    @Id
    @SequenceGenerator(sequenceName = "user_sequence",allocationSize = 1,name = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();

}
