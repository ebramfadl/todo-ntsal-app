package com.example.todo.controller;


import com.example.todo.dto.SortDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TaskDto> viewTask(@PathVariable("id") Long id){
        TaskDto response = getService().viewTask(id);
        return new ResponseEntity<TaskDto>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/by-user/{userId}")
    public ResponseEntity<List<TaskDto>> viewAllTask(@PathVariable("userId") Long userId){
        List<TaskDto> response = getService().viewAllTasks(userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/completed/{userId}")
    public ResponseEntity<List<TaskDto>> viewCompletedTasks(@PathVariable("userId") Long userId){
        List<TaskDto> response = getService().viewCompletedTasks(userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/pending/{userId}")
    public ResponseEntity<List<TaskDto>> viewPendingTasks(@PathVariable("userId") Long userId){
        List<TaskDto> response = getService().viewPendingTasks(userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/cancelled/{userId}")
    public ResponseEntity<List<TaskDto>> viewCancelledTasks(@PathVariable("userId") Long userId){
        List<TaskDto> response = getService().viewCancelledTasks(userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }


    /**
     * this api views the tasks sorted based on a specific base and type
     for example
     sort according to PRIORITY ascendingly
     base = SortBase.PRIORITY
     type = SortType.ASC
     pageNumber = a partition of the result for example page 2 will get only the second 20 elements of the aoutput
     */
    //{base}/{type}/{pageNumber}/{userId}
    //@PathVariable("base") SortBase base, @PathVariable("type") SortType type, @PathVariable("userId") Long userId,@PathVariable("pageNumber") Integer pageNumber
    @GetMapping(path = "/sort")
    public ResponseEntity<List<TaskDto>> sortTasks(@RequestBody SortDto sortDto){
        List<TaskDto> tasks = getService().sort(sortDto);
        return new ResponseEntity<List<TaskDto>>(tasks, HttpStatus.OK);
    }

    /**
     * view the tasks that their deadline is at the specified date
     */
    @GetMapping(path = "/day/{userId}")
    public ResponseEntity<List<TaskDto>> viewTasksAtDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,@PathVariable("userId") Long userId){
        List<TaskDto> response = getService().viewTasksAtDay(date,userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    /**
     search according to the description of the task by a keyword
     */
    @GetMapping(path = "/search/{keyword}/{userId}")
    public ResponseEntity<List<TaskDto>> search(@PathVariable("keyword") String keyword, @PathVariable("userId") Long userId){
        List<TaskDto> response = getService().search(keyword,userId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/by-tag/{tagId}")
    public ResponseEntity<List<TaskDto>> findByTag(@PathVariable("tag") Long tagId){
        List<TaskDto> response = getService().findByTag(tagId);
        return new ResponseEntity<List<TaskDto>>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskPostDto taskPostDto){
        TaskDto response = getService().create(taskPostDto);
        return new ResponseEntity<TaskDto>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/complete/{taskId}")
    public ResponseEntity<Boolean> markAsCompleted(@PathVariable("taskId") Long taskId){
        boolean response = getService().markAsCompleted(taskId);
        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskPostDto taskPostDto){
        TaskDto response = getService().update(taskId,taskPostDto);
        return new ResponseEntity<TaskDto>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("taskId") Long taskId){
        TaskDto response = getService().deleteTask(taskId);
        return new ResponseEntity<TaskDto>(response, HttpStatus.OK);
    }



}
