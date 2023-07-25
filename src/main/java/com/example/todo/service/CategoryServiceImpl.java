package com.example.todo.service;
import com.example.todo.dao.repo.CategoryRepo;
import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.enums.TaskStatus;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
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
        Optional<Category> optional = getCategoryRepo().findById(categoryId);
        if(!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        Category category = optional.get();
        if(categoryDto.getTitle() != null)
            category.setTitle(categoryDto.getTitle());
        if (categoryDto.getDescription() != null)
            category.setDescription(categoryDto.getDescription());
        category.setLastModifiedDate(LocalDateTime.now());
        return getCategoryMapper().entityToDto(category);
    }

    @Override
    @Transactional
    public CategoryDto deleteCategory(Long categoryId) {
        Optional<Category> optional = getCategoryRepo().findById(categoryId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        Category category = optional.get();
        category.setLastModifiedDate(LocalDateTime.now());
        getCategoryRepo().deleteById(categoryId);
        return getCategoryMapper().entityToDto(category);
    }

    @Override
    public CategoryDto viewCategory(Long categoryId) {
        Optional<Category> optional = getCategoryRepo().findById(categoryId);
        if(!optional.isPresent()){
            throw new ApiRequestException("Category does not exist!");
        }
        Category category = optional.get();
        return getCategoryMapper().entityToDto(category);
    }

    @Override
    public List<CategoryDto> viewAllCategories(Long userId) {
        List<Category> categories = getCategoryRepo().findCategoriesByUserId(userId);
        return categories.stream().map(categoryMapper::entityToDto).collect(Collectors.toList());
    }
}
