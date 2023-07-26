package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Data
public class SystemUserDto {

    @NotNull(message = "You need to provide username")
    private String username;
    private String password;
}
