package com.example.todo.service;


import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dto.SystemUserDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.SystemUser;
import com.example.todo.transformer.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Data
public class UserServiceImpl implements UserService{

    private final SystemUserRepo repo;
    private final UserMapper mapper;

    public UserServiceImpl(SystemUserRepo repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public SystemUserDto register(SystemUserDto systemUserDto) {
        SystemUser user = getMapper().dtoToEntity(systemUserDto);
        getRepo().save(user);
        return getMapper().entityToDto(user);
    }

    @Override
    public SystemUserDto viewUser(Long userId) {
        Optional<SystemUser> optional = getRepo().findById(userId);
        if (!optional.isPresent()){
            throw new ApiRequestException("User does not exist!");
        }
        SystemUser user = optional.get();
        return getMapper().entityToDto(user);
    }
}
