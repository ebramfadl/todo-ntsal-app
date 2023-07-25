package com.example.todo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @SequenceGenerator(sequenceName = "tag_sequence",allocationSize = 1,name = "tag_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence")
    private Long id;

    @Size(max = 100)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<Task> tasks;
}
