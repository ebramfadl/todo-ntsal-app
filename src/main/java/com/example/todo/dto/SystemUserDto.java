package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Data
public class SystemUserDto {

    @NotBlank
    @NotNull(message = "You need to provide username")
    private String username;

    @NotBlank
    @NotNull(message = "You need to provide a password")
    private String password;
}
