package com.example.todo.controller;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {

    @Autowired
    private UserService service;

    public SystemUserController(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

    @GetMapping(path = "/{id}")
    public SystemUserDto viewUser(@PathVariable("id") Long id){
        return getService().viewUser(id);
    }

    @PostMapping
    public SystemUserDto register(@RequestBody SystemUserDto systemUserDto){
        return getService().register(systemUserDto);
    }

}
