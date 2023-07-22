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
@AllArgsConstructor
@Data
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(path = "/view-category/{categoryId}")
    public CategoryDto viewCategory(@PathVariable("categoryId") Long categoryId){
        return getService().viewCategory(categoryId);
    }

    @GetMapping(path = "/view-all-category/{userId}")
    public List<CategoryDto> viewAllCategories(@PathVariable("userId") Long userId){
        return getService().viewAllCategories(userId);
    }

    @PostMapping(path = "/create")
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        return getService().create(categoryDto);
    }

    @PutMapping(path = "/update/{categoryId}")
    public CategoryDto update(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDto categoryDto){
        return getService().update(categoryId,categoryDto);
    }

    @DeleteMapping(path = "/delete/{categoryId}")
    public CategoryDto deleteCategory(@PathVariable("categoryId") Long categoryId){
        return getService().deleteCategory(categoryId);
    }

}
