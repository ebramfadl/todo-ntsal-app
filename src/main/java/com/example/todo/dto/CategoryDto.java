package com.example.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CategoryDto {

    @NotNull(message = "You have to provide a title!")
    private String title;
    private String description;
    private Long userId;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}
