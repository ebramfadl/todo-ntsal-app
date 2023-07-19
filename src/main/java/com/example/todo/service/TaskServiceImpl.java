package com.example.todo.service;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.model.Task;
import com.example.todo.transformer.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class TaskServiceImpl implements TaskService{

    private TaskRepo repo;
    private TaskMapper mapper;


    @Override
    public TaskDto viewTask(Long id) {
        Task task = getRepo().findById(id).get();
        if(task == null){
            throw  new IllegalStateException("Task does not exist!");
        }
        return getMapper().entityToDto(task);
    }

    @Override
    public List<TaskDto> viewAllTasks(Long userId) {
        List<Task> allTasks = getRepo().findTasksByUserId(userId);
        return allTasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto create(TaskPostDto taskPostDto) {
        return null;
    }
}
