package com.example.todo.service;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.enums.TaskStatus;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.TodoList;
import com.example.todo.model.Task;
import com.example.todo.transformer.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class TaskServiceImpl implements TaskService{

    private TaskRepo repo;
    private TaskMapper mapper;


    @Override
    public TaskDto viewTask(Long id) {
        Optional<Task> optional = getRepo().findById(id);
        if(!optional.isPresent()){
            throw  new ApiRequestException("Task does not exist!");
        }
        Task task = optional.get();
        return getMapper().entityToDto(task);
    }

    @Override
    public List<TaskDto> viewAllTasks(Long userId) {
        List<Task> allTasks = getRepo().findTasksByUserId(userId);
        return allTasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDto create(TaskPostDto taskPostDto) {
        Task task = getMapper().dtoToEntity(taskPostDto);
        getRepo().save(task);
        return getMapper().entityToDto(task);
    }

    @Override
    @Transactional
    public boolean markAsCompleted(Long taskId) {
        Optional<Task> optional = getRepo().findById(taskId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Task does not exist!");
        }
        Task task = optional.get();
        task.setStatus(TaskStatus.COMPLETED);
        task.setDateCompleted(LocalDateTime.now());
        task.setLastModifiedDate(LocalDateTime.now());
        return true;
    }

    @Override
    @Transactional
    public TaskDto deleteTask(Long taskId) {
        Optional<Task> optional = getRepo().findById(taskId);
        if (!optional.isPresent()){
            throw new ApiRequestException("Task does not exist!");
        }
        Task task = optional.get();
        task.setStatus(TaskStatus.CANCELLED);
        task.setLastModifiedDate(LocalDateTime.now());
        getRepo().deleteById(taskId);
        return getMapper().entityToDto(task);
    }

    @Override
    public List<TaskDto> viewCompletedTasks(Long userId) {
        List<Task> tasks = getRepo().findCompletedTasksByStatusAndUserId(TaskStatus.COMPLETED,userId,LocalDateTime.now().minusMonths(1));
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> viewPendingTasks(Long userId) {
        List<Task> tasks = getRepo().findTasksByStatusAndUserId(TaskStatus.PENDING,userId);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> viewCancelledTasks(Long userId) {
        List<Task> tasks = getRepo().findTasksByStatusAndUserId(TaskStatus.CANCELLED,userId);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDto update(Long taskId, TaskPostDto taskPostDto) {
        Optional<Task> optional = getRepo().findById(taskId);
        Task task;
        if(!optional.isPresent()){
            throw new ApiRequestException("Task does not exist!");
        }
        else {
            task = optional.get();
            if(taskPostDto.getTag() != null)
                task.setTag(taskPostDto.getTag());
            if (taskPostDto.getDescription() != null)
                task.setDescription(taskPostDto.getDescription());
            if (taskPostDto.getDeadline() != null)
                task.setDeadline(taskPostDto.getDeadline());
            if (taskPostDto.getStatus() != null)
                task.setStatus(taskPostDto.getStatus());
            if (taskPostDto.getPriority() != null)
                task.setPriority(taskPostDto.getPriority());
            if (taskPostDto.getCategoryId() != null){
                TodoList todoList = getRepo().findUserCategory(taskPostDto.getCategoryId(),task.getUser().getId());
                task.setTodoList(todoList);
            }
            task.setLastModifiedDate(LocalDateTime.now());
        }
        return getMapper().entityToDto(task);
    }

    @Override
    public List<TaskDto> sort(SortBase base, SortType type, Long userId) {
        List<Task> tasks = getRepo().findTasksByUserId(userId);
        if (base == SortBase.DEADLINE){
             tasks.sort(Comparator.comparing(Task::getDeadline));
        }
        else if (base == SortBase.ALPHAPETICALLY){
            tasks.sort(Comparator.comparing(Task::getTag,String.CASE_INSENSITIVE_ORDER));
        }
        else if (base == SortBase.PRIORITY) {
            tasks.sort(Comparator.comparing(task -> task.getPriority().ordinal()));
        }
        if(type == SortType.DESC)
            Collections.reverse(tasks);

        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> viewTasksAtDay(LocalDate date, Long userId) {
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);
        List<Task> tasks = getRepo().findByDeadlineBetweenAndUserId(startOfDay, endOfDay,userId);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> search(String keyword, Long userID) {
        List<Task> tasks = getRepo().findByDescriptionContainingIgnoreCaseAndUserId(keyword,userID);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByTag(String tag) {
        List<Task> tasks = getRepo().findTasksByTagIgnoreCase(tag);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }


}
