package com.example.todo.service;


import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;

import java.util.List;

public interface ReminderService {
    ReminderDto create(ReminderPostDto reminderPostDto);

    ReminderDto deleteReminder(Long reminderId);

    ReminderDto update(Long reminderId, ReminderPostDto reminderPostDto);

    ReminderDto viewReminder(Long reminderId);

    List<ReminderDto> viewAllReminders(Long userId);
}
