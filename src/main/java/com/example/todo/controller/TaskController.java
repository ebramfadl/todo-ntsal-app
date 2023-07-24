package com.example.todo.controller;


import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public TaskService getService() {
        return service;
    }

    @GetMapping(path = "/{id}")
    public TaskDto viewTask(@PathVariable("id") Long id){
        return getService().viewTask(id);
    }

    @GetMapping(path = "/by-user/{userId}")
    public List<TaskDto> viewAllTask(@PathVariable("userId") Long userId){
        return getService().viewAllTasks(userId);
    }

    @GetMapping(path = "/completed/{userId}")
    public List<TaskDto> viewCompletedTasks(@PathVariable("userId") Long userId){
        return getService().viewCompletedTasks(userId);
    }

    @GetMapping(path = "/pending/{userId}")
    public List<TaskDto> viewPendingTasks(@PathVariable("userId") Long userId){
        return getService().viewPendingTasks(userId);
    }

    @GetMapping(path = "/cancelled/{userId}")
    public List<TaskDto> viewCancelledTasks(@PathVariable("userId") Long userId){
        return getService().viewCancelledTasks(userId);
    }

    @GetMapping(path = "/sort/{base}/{type}/{userId}")
    public List<TaskDto> sortTasks(@PathVariable("base") SortBase base, @PathVariable("type") SortType type, @PathVariable("userId") Long userId){
        return getService().sort(base,type,userId);
    }

    @GetMapping(path = "/day/{userId}")
    public List<TaskDto> viewTasksAtDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,@PathVariable("userId") Long userId){
        return getService().viewTasksAtDay(date,userId);
    }

    @GetMapping(path = "/search/{keyword}/{userId}")
    public List<TaskDto> search(@PathVariable("keyword") String keyword, @PathVariable("userId") Long userId){
        return getService().search(keyword,userId);
    }

    @GetMapping(path = "/by-tag/{tag}")
    public List<TaskDto> findByTag(@PathVariable("tag") String tag){
        return getService().findByTag(tag);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskPostDto taskPostDto){

        return getService().create(taskPostDto);
    }

    @PutMapping(path = "/complete/{taskId}")
    public boolean markAsCompleted(@PathVariable("taskId") Long taskId){
        return getService().markAsCompleted(taskId);
    }

    @PutMapping(path = "/{taskId}")
    public TaskDto updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskPostDto taskPostDto){
        return getService().update(taskId,taskPostDto);
    }

    @DeleteMapping(path = "/{taskId}")
    public TaskDto deleteTask(@PathVariable("taskId") Long taskId){
        return getService().deleteTask(taskId);
    }



}
