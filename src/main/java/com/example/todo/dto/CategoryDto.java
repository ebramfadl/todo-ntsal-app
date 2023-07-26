package com.example.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CategoryDto {

    @NotBlank
    @NotNull(message = "You have to provide a title!")
    private String title;

    @NotBlank
    @NotNull(message = "You need to provide a description")
    private String description;

    @NotBlank
    @NotNull(message = "You need to provide the userId")
    private Long userId;

    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}
