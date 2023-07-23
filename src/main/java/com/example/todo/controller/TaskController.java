package com.example.todo.controller;


import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path = "/view-completed/{userId}")
    public List<TaskDto> viewCompletedTasks(@PathVariable("userId") Long userId){
        return getService().viewCompletedTasks(userId);
    }

    @GetMapping(path = "/view-pending/{userId}")
    public List<TaskDto> viewPendingTasks(@PathVariable("userId") Long userId){
        return getService().viewPendingTasks(userId);
    }

    @GetMapping(path = "/view-cancelled/{userId}")
    public List<TaskDto> viewCancelledTasks(@PathVariable("userId") Long userId){
        return getService().viewCancelledTasks(userId);
    }

    @GetMapping(path = "/sort/{base}/{type}/{userId}")
    public List<TaskDto> sortTasks(@PathVariable("base") SortBase base, @PathVariable("type") SortType type, @PathVariable("userId") Long userId){
        return getService().sort(base,type,userId);
    }

    @GetMapping(path = "/view-tasks-day")
    public List<TaskDto> viewTasksAtDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        return getService().viewTasksAtDay(date);
    }

    @GetMapping(path = "/search/{keyword}")
    public List<TaskDto> search(@PathVariable("keyword") String keyword){
        return getService().search(keyword);
    }

    @PostMapping(path = "/create")
    public TaskDto createTask(@RequestBody TaskPostDto taskPostDto){
        return getService().create(taskPostDto);
    }

    @PutMapping(path = "/mark-completed/{taskId}")
    public boolean markAsCompleted(@PathVariable("taskId") Long taskId){
        return getService().markAsCompleted(taskId);
    }

    @PutMapping(path = "/update/{taskId}")
    public TaskDto updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskPostDto taskPostDto){
        return getService().update(taskId,taskPostDto);
    }

    @DeleteMapping(path = "/delete/{taskId}")
    public TaskDto deleteTask(@PathVariable("taskId") Long taskId){
        return getService().deleteTask(taskId);
    }



}
