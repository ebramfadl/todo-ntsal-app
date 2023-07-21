package com.example.todo.service;

import com.example.todo.dao.repo.CategoryRepo;
import com.example.todo.dto.CategoryDto;
import com.example.todo.model.Category;
import com.example.todo.transformer.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Data
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = getCategoryMapper().dtoToEntity(categoryDto);
        getCategoryRepo().save(category);
        return getCategoryMapper().entityToDto(category);
    }

    @Override
    @Transactional
    public CategoryDto update(Long categoryId,CategoryDto categoryDto) {
        Category category = getCategoryRepo().findById(categoryId).get();
        if(category == null){
            throw new IllegalStateException("Category does not exist!");
        }
        if(categoryDto.getTitle() != null)
            category.setTitle(categoryDto.getTitle());
        if (categoryDto.getDescription() != null)
            category.setDescription(categoryDto.getDescription());

        return getCategoryMapper().entityToDto(category);
    }
}
