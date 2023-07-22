package com.example.todo.controller;


import com.example.todo.dto.*;
import com.example.todo.service.ReminderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminder")
@AllArgsConstructor
@Data
public class ReminderController {

    @Autowired
    private ReminderService service;

    @GetMapping(path = "/view-reminder/{reminderId}")
    public ReminderDto viewReminder(@PathVariable("reminderId") Long reminderId){
        return getService().viewReminder(reminderId);
    }

    @GetMapping(path = "/view-all-reminders/{userId}")
    public List<ReminderDto> viewAllReminders(@PathVariable("userId") Long userId){
        return getService().viewAllReminders(userId);
    }

    @PostMapping(path = "/create")
    public ReminderDto create(@RequestBody ReminderPostDto reminderPostDto){
        return getService().create(reminderPostDto);
    }

    @DeleteMapping(path = "/delete/{reminderId}")
    public ReminderDto deleteReminder(@PathVariable("reminderId") Long reminderId){
        return getService().deleteReminder(reminderId);
    }

    @PutMapping(path = "/update/{reminderId}")
    public ReminderDto updateReminder(@PathVariable("reminderId") Long reminderId, @RequestBody ReminderPostDto reminderPostDto){
        return getService().update(reminderId,reminderPostDto);
    }
}
