package com.example.todo.service;

import com.example.todo.dto.SystemUserDto;

public interface UserService {

    public SystemUserDto register(SystemUserDto systemUserDto);
    public SystemUserDto viewUser(Long userId);
}
