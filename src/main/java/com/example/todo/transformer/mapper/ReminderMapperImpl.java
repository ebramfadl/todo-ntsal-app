package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.model.Reminder;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Data
public class ReminderMapperImpl implements ReminderMapper{

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public ReminderDto entityToDto(Reminder reminder) {

        return new ReminderDto(reminder.getTitle(),reminder.getDueDate(),reminder.getDateCreated(),reminder.getLastModifiedDate(),reminder.getRepetitionType(),reminder.getStatus());
    }

    @Override
    public Reminder dtoToEntity(ReminderPostDto reminderPostDto) {
        Optional<Task> optional = getTaskRepo().findById(reminderPostDto.getTaskId());
        if(!optional.isPresent()){
            throw new IllegalStateException("Task does not exist");
        }
        Task task = optional.get();
        if (!reminderPostDto.getDueDate().isBefore(task.getDeadline())){
            throw new IllegalStateException("You cannot create a reminder with a due date greater than the task deadline!");
        }
        return new Reminder(reminderPostDto.getTitle(),reminderPostDto.getRepetitionType(),reminderPostDto.getDueDate(),task);
    }

}
