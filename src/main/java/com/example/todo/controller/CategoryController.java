package com.example.todo.controller;

import com.example.todo.dto.CategoryDto;
import com.example.todo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryDto> viewCategory(@PathVariable("categoryId") Long categoryId){
        CategoryDto response = getService().viewCategory(categoryId);
        return new ResponseEntity<CategoryDto>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/by-user/{userId}")
    public ResponseEntity<List<CategoryDto>> viewAllCategories(@PathVariable("userId") Long userId){
        List<CategoryDto> response = getService().viewAllCategories(userId);
        return new ResponseEntity<List<CategoryDto>>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
        CategoryDto response = getService().create(categoryDto);
        return new ResponseEntity<CategoryDto>(response,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto response = getService().update(categoryId,categoryDto);
        return new ResponseEntity<CategoryDto>(response,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("categoryId") Long categoryId){
        CategoryDto response = getService().deleteCategory(categoryId);
        return new ResponseEntity<CategoryDto>(response,HttpStatus.OK);
    }

}
