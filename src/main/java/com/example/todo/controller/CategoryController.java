package com.example.todo.controller;

import com.example.todo.dto.CategoryDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@Data
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(path = "/create")
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        return getService().create(categoryDto);
    }

    @PutMapping(path = "/update/{categoryId}")
    public CategoryDto update(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDto categoryDto){
        return getService().update(categoryId,categoryDto);
    }

}
