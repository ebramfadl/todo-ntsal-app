package com.example.todo.service;


import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dto.SystemUserDto;
import com.example.todo.model.SystemUser;
import com.example.todo.transformer.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService{

    @Autowired
    private SystemUserRepo repo;
    @Autowired
    private UserMapper mapper;


    @Override
    @Transactional
    public SystemUserDto register(SystemUserDto systemUserDto) {
        SystemUser user = getMapper().dtoToEntity(systemUserDto);
        getRepo().save(user);
        return getMapper().entityToDto(user);
    }

    @Override
    public SystemUserDto viewUser(Long userId) {
        SystemUser user = getRepo().findById(userId).get();
        return getMapper().entityToDto(user);
    }
}
