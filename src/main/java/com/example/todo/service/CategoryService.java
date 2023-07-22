package com.example.todo.service;

import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(Long categoryId,CategoryDto categoryDto);

    CategoryDto deleteCategory(Long categoryId);

    CategoryDto viewCategory(Long categoryId);

    List<CategoryDto> viewAllCategories(Long userId);
}
