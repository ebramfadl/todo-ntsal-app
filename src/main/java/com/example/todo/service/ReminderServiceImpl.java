package com.example.todo.service;


import com.example.todo.dao.repo.ReminderRepo;
import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.Reminder;
import com.example.todo.transformer.mapper.ReminderMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ReminderServiceImpl implements ReminderService{

    private final ReminderRepo repo;

    private final ReminderMapper mapper;

    public ReminderServiceImpl(ReminderRepo repo, ReminderMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ReminderDto create(ReminderPostDto reminderPostDto) {
        Reminder reminder = getMapper().dtoToEntity(reminderPostDto);
        getRepo().save(reminder);
        return getMapper().entityToDto(reminder);
    }

    @Override
    @Transactional
    public ReminderDto deleteReminder(Long reminderId) {
        Optional<Reminder> optional = getRepo().findById(reminderId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Reminder does not exist!");
        }
        Reminder reminder = optional.get();
        reminder.setLastModifiedDate(LocalDateTime.now());
        getRepo().deleteById(reminderId);
        return getMapper().entityToDto(reminder);
    }

    @Override
    @Transactional
    public ReminderDto update(Long reminderId, ReminderPostDto reminderPostDto) {
        Optional<Reminder> optional = getRepo().findById(reminderId);
        Reminder reminder;
        if(!optional.isPresent()){
            throw new ApiRequestException("Reminder does not exist!");
        }
        else {
            reminder = optional.get();
            if (reminderPostDto.getDueDate() != null)
                reminder.setDueDate(reminderPostDto.getDueDate());

            reminder.setLastModifiedDate(LocalDateTime.now());
        }
        return getMapper().entityToDto(reminder);
    }

    @Override
    public ReminderDto viewReminder(Long reminderId) {
        Optional<Reminder> optional = getRepo().findById(reminderId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Reminder does not exist");
        }
        Reminder reminder = optional.get();
        return getMapper().entityToDto(reminder);
    }

    @Override
    public List<ReminderDto> viewAllReminders(Long userId) {
        List<Reminder> reminders = getRepo().findUserReminders(userId);
        return reminders.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}
