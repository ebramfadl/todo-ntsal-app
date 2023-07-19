package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;

import java.util.List;

public interface TaskService {
    public TaskDto viewTask(Long id);

    public List<TaskDto> viewAllTasks(Long userId);

    TaskDto create(TaskPostDto taskPostDto);
}
