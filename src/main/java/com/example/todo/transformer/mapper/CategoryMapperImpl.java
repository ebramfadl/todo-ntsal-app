package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.Category;
import com.example.todo.model.SystemUser;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class CategoryMapperImpl implements CategoryMapper{

    private SystemUserRepo systemUserRepo;

    @Override
    public CategoryDto entityToDto(Category category) {

        return new CategoryDto(category.getTitle(),category.getDescription(),category.getUser().getId(),category.getDateCreated(),category.getLastModifiedDate());
    }

    @Override
    public Category dtoToEntity(CategoryDto categoryDto) {
        SystemUser user = getSystemUserRepo().findById(categoryDto.getUserId()).get();
        return new Category(categoryDto.getTitle(),categoryDto.getDescription(),user);
    }
}
