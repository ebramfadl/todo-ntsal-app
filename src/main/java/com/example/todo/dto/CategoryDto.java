package com.example.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CategoryDto {

    private String title;
    private String description;
    private Long userId;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}
