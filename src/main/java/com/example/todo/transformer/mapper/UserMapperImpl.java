package com.example.todo.transformer.mapper;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.model.SystemUser;
import org.springframework.stereotype.Component;


@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public SystemUserDto entityToDto(SystemUser systemUser) {

        return new SystemUserDto(systemUser.getUsername(),systemUser.getPassword());
    }

    @Override
    public SystemUser dtoToEntity(SystemUserDto systemUserDto) {

        return new SystemUser(systemUserDto.getUsername(),systemUserDto.getPassword());
    }
}
