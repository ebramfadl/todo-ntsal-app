package com.example.todo.service;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.transformer.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class TaskServiceImpl implements TaskService{

    private TaskRepo repo;
    private TaskMapper mapper;


    @Override
    public TaskDto viewTask(Long id) {
        return null;
    }
}
