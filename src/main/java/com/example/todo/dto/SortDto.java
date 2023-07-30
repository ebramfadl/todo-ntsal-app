package com.example.todo.dto;

import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class SortDto {

    @NotNull(message = "UserId is required!")
    @NotBlank
    private Long userId;

    @NotNull(message = "Sort base is required!")
    @NotBlank
    private SortBase base;

    @NotNull(message = "Sort type is required!")
    @NotBlank
    private SortType type;

    @NotNull(message = "Page number is required!")
    @NotBlank
    private Integer pageNumber;

}
