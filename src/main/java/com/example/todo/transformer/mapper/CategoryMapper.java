package com.example.todo.transformer.mapper;

import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.Category;
import com.example.todo.model.Task;

import java.util.Locale;

public interface CategoryMapper {

    public CategoryDto entityToDto(Category category);
    public Category dtoToEntity(CategoryDto categoryDto);
}
