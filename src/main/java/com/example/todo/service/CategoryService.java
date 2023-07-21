package com.example.todo.service;

import com.example.todo.dto.CategoryDto;

public interface CategoryService {

    public CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(Long categoryId,CategoryDto categoryDto);
}
