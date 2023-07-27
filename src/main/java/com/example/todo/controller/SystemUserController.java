package com.example.todo.controller;

import com.example.todo.dto.SystemUserDto;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Validated
public class SystemUserController {

    @Autowired
    private final UserService service;

    public SystemUserController(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SystemUserDto> viewUser(@PathVariable("id") Long id){
        SystemUserDto response = getService().viewUser(id);
        return new ResponseEntity<SystemUserDto>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemUserDto> register(@RequestBody @Valid SystemUserDto systemUserDto){
        SystemUserDto user = getService().register(systemUserDto);
        return new ResponseEntity<SystemUserDto>(user,HttpStatus.CREATED);
    }

}
