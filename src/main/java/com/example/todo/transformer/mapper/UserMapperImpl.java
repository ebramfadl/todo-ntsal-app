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

        if (systemUserDto.getUsername() == null || systemUserDto.getPassword() == null){
            throw new IllegalStateException("You need to provide both username and password!");
        }
        return new SystemUser(systemUserDto.getUsername(),systemUserDto.getPassword());
    }
}
