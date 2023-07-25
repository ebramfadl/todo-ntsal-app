package com.example.todo.controller;

import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    public CategoryService getService() {
        return service;
    }

    @GetMapping(path = "/{categoryId}")
    public CategoryDto viewCategory(@PathVariable("categoryId") Long categoryId){
        return getService().viewCategory(categoryId);
    }

    @GetMapping(path = "/by-user/{userId}")
    public List<CategoryDto> viewAllCategories(@PathVariable("userId") Long userId){
        return getService().viewAllCategories(userId);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        return getService().create(categoryDto);
    }

    @PutMapping(path = "/{categoryId}")
    public CategoryDto update(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDto categoryDto){
        return getService().update(categoryId,categoryDto);
    }

    @DeleteMapping(path = "/{categoryId}")
    public CategoryDto deleteCategory(@PathVariable("categoryId") Long categoryId){
        return getService().deleteCategory(categoryId);
    }

}
