package com.example.todo.controller;


import com.example.todo.dto.TaskDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
@Data
public class TaskController {

    private TaskService service;

    @GetMapping(path = "/view-task/{id}")
    public TaskDto viewTask(@PathVariable("id") Long id){
        return getService().viewTask(id);
    }

    @GetMapping(path = "/view-all-tasks/{userId}")
    public List<TaskDto> viewAllTask(@PathVariable("userId") Long userId){
        return getService().viewAllTasks(userId);
    }

    @PostMapping(path = "/add")
    public TaskDto addTask(@RequestBody TaskDto taskDto){
        return getService().add(taskDto);
    }

    @PutMapping(path = "/mark-completed/{taskId}")
    public boolean markAsCompleted(@PathVariable("taskId") Long taskId){
        return getService().markAsCompleted(taskId);
    }

    @PutMapping(path = "/update/{taskId}")
    public TaskDto updateTask(@PathVariable("taskId") Long taskId){
        return getService().update(taskId);
    }

    @DeleteMapping(path = "/delete/{taskId}")
    public TaskDto deleteTask(@PathVariable("taskId") Long taskId){
        return getService().deleteTask(taskId);
    }

    @GetMapping(path = "/sort/{base}/{type}")
    public List<TaskDto> sortTasks(@PathVariable("base") SortBase base, @PathVariable("type") SortType type){
        return getService().sort(base,type);
    }

}
