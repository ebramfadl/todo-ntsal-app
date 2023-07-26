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
    private final TaskService service;

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


    /**
     * this api views the tasks sorted based on a specific base and type
     for example
     sort according to PRIORITY ascendingly
     base = SortBase.PRIORITY
     type = SortType.ASC
     pageNumber = a partition of the result for example page 2 will get only the second 20 elements of the aoutput
     */
    @GetMapping(path = "/sort/{base}/{type}/{pageNumber}/{userId}")
    public List<TaskDto> sortTasks(@PathVariable("base") SortBase base, @PathVariable("type") SortType type, @PathVariable("userId") Long userId,@PathVariable("pageNumber") Integer pageNumber){
        List<TaskDto> allTasks = getService().sort(base,type,userId);
        List<TaskDto> list = allTasks.subList((pageNumber-1)*20,Math.min(allTasks.size(), pageNumber*20));
        return  list;
    }

    /**
     * view the tasks that their deadline is at the specified date
     */
    @GetMapping(path = "/day/{userId}")
    public List<TaskDto> viewTasksAtDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,@PathVariable("userId") Long userId){
        return getService().viewTasksAtDay(date,userId);
    }

    /**
     search according to the description of the task by a keyword
     */
    @GetMapping(path = "/search/{keyword}/{userId}")
    public List<TaskDto> search(@PathVariable("keyword") String keyword, @PathVariable("userId") Long userId){
        return getService().search(keyword,userId);
    }

    @GetMapping(path = "/by-tag/{tagId}")
    public List<TaskDto> findByTag(@PathVariable("tag") Long tagId){
        return getService().findByTag(tagId);
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
