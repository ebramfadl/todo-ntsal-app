package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.Reminder;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReminderMapperImpl implements ReminderMapper{

    @Autowired
    private TaskRepo taskRepo;

    public ReminderMapperImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public TaskRepo getTaskRepo() {
        return taskRepo;
    }

    @Override
    public ReminderDto entityToDto(Reminder reminder) {
        return new ReminderDto(reminder.getDescription(),reminder.getDueDate(),reminder.getDateCreated(),reminder.getLastModifiedDate());
    }

    @Override
    public Reminder dtoToEntity(ReminderPostDto reminderPostDto) {
        Optional<Task> optional = getTaskRepo().findById(reminderPostDto.getTaskId());
        if (reminderPostDto.getTaskId() == null){
            throw new ApiRequestException("You need to provide the task of the reminder!");
        }
        if (reminderPostDto.getDueDate() == null){
            throw new ApiRequestException("You need to provide the deadline for the reminder!");
        }
        if(!optional.isPresent()){
            throw new ApiRequestException("Task does not exist");
        }

        Task task = optional.get();
        if (!reminderPostDto.getDueDate().isBefore(task.getDeadline())){
            throw new ApiRequestException("You cannot create a reminder with a due date greater than the task deadline!");
        }

        return new Reminder(reminderPostDto.getDueDate(),task);
    }

}
