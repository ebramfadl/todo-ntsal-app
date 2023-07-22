package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    public TaskDto viewTask(Long id);

    public List<TaskDto> viewAllTasks(Long userId);

    TaskDto create(TaskPostDto taskPostDto);

    boolean markAsCompleted(Long taskId);

    TaskDto deleteTask(Long taskId);

    List<TaskDto> viewCompletedTasks(Long userId);

    List<TaskDto> viewPendingTasks(Long userId);

    List<TaskDto> viewCancelledTasks(Long userId);

    TaskDto update(Long taskId, TaskPostDto taskPostDto);

    List<TaskDto> sort(SortBase base, SortType type, Long userId);

    List<TaskDto> viewTasksAtDay(LocalDate date);

    List<TaskDto> search(String keyword);
}
