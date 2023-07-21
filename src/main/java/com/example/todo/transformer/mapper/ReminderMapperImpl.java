package com.example.todo.transformer.mapper;

import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.Reminder;
import com.example.todo.model.Task;
import org.springframework.stereotype.Component;

@Component
public class ReminderMapperImpl implements ReminderMapper{
    @Override
    public ReminderDto entityToDto(Reminder reminder) {
        return null;
    }

    @Override
    public Reminder dtoToEntity(ReminderDto reminderDto) {
        return null;
    }
}
