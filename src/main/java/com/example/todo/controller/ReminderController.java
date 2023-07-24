package com.example.todo.controller;


import com.example.todo.dto.*;
import com.example.todo.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminder")
public class ReminderController {

    @Autowired
    private ReminderService service;

    public ReminderController(ReminderService service) {
        this.service = service;
    }

    public ReminderService getService() {
        return service;
    }

    @GetMapping(path = "/{reminderId}")
    public ReminderDto viewReminder(@PathVariable("reminderId") Long reminderId){
        return getService().viewReminder(reminderId);
    }

    @GetMapping(path = "/by-user/{userId}")
    public List<ReminderDto> viewAllReminders(@PathVariable("userId") Long userId){
        return getService().viewAllReminders(userId);
    }

    @PostMapping
    public ReminderDto create(@RequestBody ReminderPostDto reminderPostDto){
        return getService().create(reminderPostDto);
    }

    @DeleteMapping(path = "/{reminderId}")
    public ReminderDto deleteReminder(@PathVariable("reminderId") Long reminderId){
        return getService().deleteReminder(reminderId);
    }

    @PutMapping(path = "/{reminderId}")
    public ReminderDto updateReminder(@PathVariable("reminderId") Long reminderId, @RequestBody ReminderPostDto reminderPostDto){
        return getService().update(reminderId,reminderPostDto);
    }
}
