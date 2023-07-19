package com.example.todo.transformer.mapper;

import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.SystemUserDto;
import com.example.todo.model.Reminder;
import com.example.todo.model.SystemUser;

public interface SystemUserMapper {

    SystemUserDto entityToDto(SystemUser systemUser);
    SystemUser dtoToEntity(SystemUserDto systemUserDto);
}
