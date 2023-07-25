package com.example.todo.transformer.mapper;

import com.example.todo.dto.CategoryDto;
import com.example.todo.model.TodoList;

public interface CategoryMapper {

    public CategoryDto entityToDto(TodoList todoList);
    public TodoList dtoToEntity(CategoryDto categoryDto);
}
