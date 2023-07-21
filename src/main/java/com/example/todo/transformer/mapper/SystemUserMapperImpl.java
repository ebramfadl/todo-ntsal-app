package com.example.todo.transformer.mapper;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.SystemUser;
import com.example.todo.model.Task;
import org.springframework.stereotype.Component;


@Component
public class SystemUserMapperImpl implements SystemUserMapper{
    @Override
    public SystemUserDto entityToDto(SystemUser systemUser) {
        return null;
    }

    @Override
    public SystemUser dtoToEntity(SystemUserDto systemUserDto) {
        return null;
    }
}
