package com.example.todo.controller;


import com.example.todo.dto.*;
import com.example.todo.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminder")
public class ReminderController {

    @Autowired
    private final ReminderService service;

    public ReminderController(ReminderService service) {

        this.service = service;
    }

    public ReminderService getService() {

        return service;
    }

    @GetMapping(path = "/{reminderId}")
    public ResponseEntity<ReminderDto> viewReminder(@PathVariable("reminderId") Long reminderId){
        ReminderDto response = getService().viewReminder(reminderId);
        return new ResponseEntity<ReminderDto>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/by-user/{userId}")
    public ResponseEntity<List<ReminderDto>> viewAllReminders(@PathVariable("userId") Long userId){
        List<ReminderDto> response = getService().viewAllReminders(userId);
        return new ResponseEntity<List<ReminderDto>>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReminderDto> create(@RequestBody ReminderPostDto reminderPostDto){
        ReminderDto response = getService().create(reminderPostDto);
        return new ResponseEntity<ReminderDto>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{reminderId}")
    public ResponseEntity<ReminderDto> deleteReminder(@PathVariable("reminderId") Long reminderId){
        ReminderDto response = getService().deleteReminder(reminderId);
        return new ResponseEntity<ReminderDto>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{reminderId}")
    public ResponseEntity<ReminderDto> updateReminder(@PathVariable("reminderId") Long reminderId, @RequestBody ReminderPostDto reminderPostDto){
        ReminderDto response = getService().update(reminderId,reminderPostDto);
        return new ResponseEntity<ReminderDto>(response, HttpStatus.OK);
    }
}
