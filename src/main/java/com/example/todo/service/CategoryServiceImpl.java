package com.example.todo.service;

import com.example.todo.dao.repo.CategoryRepo;
import com.example.todo.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return null;
    }
}
