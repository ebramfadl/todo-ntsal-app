package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dto.CategoryDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.TodoList;
import com.example.todo.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryMapperImpl implements CategoryMapper{

    @Autowired
    private SystemUserRepo systemUserRepo;

    public CategoryMapperImpl(SystemUserRepo systemUserRepo) {
        this.systemUserRepo = systemUserRepo;
    }

    public SystemUserRepo getSystemUserRepo() {
        return systemUserRepo;
    }

    @Override
    public CategoryDto entityToDto(TodoList todoList) {

        return new CategoryDto(todoList.getTitle(), todoList.getDescription(), todoList.getUser().getId(), todoList.getDateCreated(), todoList.getLastModifiedDate());
    }

    @Override
    public TodoList dtoToEntity(CategoryDto categoryDto) {
        Optional<SystemUser> optional = getSystemUserRepo().findById(categoryDto.getUserId());
        if (!optional.isPresent()){
            throw new ApiRequestException("User does not exist!");
        }
        if (categoryDto.getTitle() == null){
            throw new ApiRequestException("You need to provide a title for the category!");
        }
        if (categoryDto.getDescription() == null){
            throw new ApiRequestException("You need to provide a description for the category!");
        }

        SystemUser user = optional.get();
        return new TodoList(categoryDto.getTitle(),categoryDto.getDescription(),user);
    }
}
