package com.example.todo.controller;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Data
public class SystemUserController {

    private UserService service;

    @GetMapping(path = "/view-user/{id}")
    public SystemUserDto viewUser(@PathVariable("id") Long id){
        return getService().viewUser(id);
    }

    @PostMapping(path = "/register")
    public SystemUserDto register(@RequestBody SystemUserDto systemUserDto){
        return getService().register(systemUserDto);
    }
}
