package com.example.todo.transformer.mapper;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.model.SystemUser;

public interface UserMapper {

    SystemUserDto entityToDto(SystemUser systemUser);
    SystemUser dtoToEntity(SystemUserDto systemUserDto);
}
