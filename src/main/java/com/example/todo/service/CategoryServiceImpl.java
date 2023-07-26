package com.example.todo.service;
import com.example.todo.dao.repo.CategoryRepo;
import com.example.todo.dto.CategoryDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.TodoList;
import com.example.todo.transformer.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        TodoList todoList = getCategoryMapper().dtoToEntity(categoryDto);
        getCategoryRepo().save(todoList);
        return getCategoryMapper().entityToDto(todoList);
    }

    @Override
    @Transactional
    public CategoryDto update(Long categoryId,CategoryDto categoryDto) {
        Optional<TodoList> optional = getCategoryRepo().findById(categoryId);
        if(!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        TodoList todoList = optional.get();
        if(categoryDto.getTitle() != null)
            todoList.setTitle(categoryDto.getTitle());
        if (categoryDto.getDescription() != null)
            todoList.setDescription(categoryDto.getDescription());
        todoList.setLastModifiedDate(LocalDateTime.now());
        return getCategoryMapper().entityToDto(todoList);
    }

    @Override
    @Transactional
    public CategoryDto deleteCategory(Long categoryId) {
        Optional<TodoList> optional = getCategoryRepo().findById(categoryId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        TodoList todoList = optional.get();
        todoList.setLastModifiedDate(LocalDateTime.now());
        getCategoryRepo().deleteById(categoryId);
        return getCategoryMapper().entityToDto(todoList);
    }

    @Override
    public CategoryDto viewCategory(Long categoryId) {
        Optional<TodoList> optional = getCategoryRepo().findById(categoryId);
        if(!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        TodoList todoList = optional.get();
        return getCategoryMapper().entityToDto(todoList);
    }

    @Override
    public List<CategoryDto> viewAllCategories(Long userId) {
        List<TodoList> categories = getCategoryRepo().findCategoriesByUserId(userId);
        return categories.stream().map(categoryMapper::entityToDto).collect(Collectors.toList());
    }
}
