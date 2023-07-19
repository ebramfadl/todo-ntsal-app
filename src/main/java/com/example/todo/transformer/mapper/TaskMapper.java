package com.example.todo.transformer.mapper;

import com.example.todo.dto.TaskDto;
import com.example.todo.model.Task;

public interface TaskMapper {

    TaskDto entityToDto(Task task);
    Task dtoToEntity(TaskDto taskDto);
}
