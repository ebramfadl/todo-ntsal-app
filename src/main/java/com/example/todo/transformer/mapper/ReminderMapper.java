package com.example.todo.transformer.mapper;

import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.Reminder;
import com.example.todo.model.Task;

public interface ReminderMapper {

    ReminderDto entityToDto(Reminder reminder);
    Reminder dtoToEntity(ReminderPostDto reminderPostDto);
}
