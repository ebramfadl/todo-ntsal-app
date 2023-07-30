package com.example.todo.service;

import com.example.todo.dao.repo.TagRepo;
import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.SortDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.enums.TaskStatus;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.Tag;
import com.example.todo.model.TodoList;
import com.example.todo.model.Task;
import com.example.todo.transformer.mapper.TaskMapper;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class TaskServiceImpl implements TaskService{

    private final TaskRepo repo;
    private final TaskMapper mapper;
    private final TagRepo tagRepo;

    public TaskServiceImpl(TaskRepo repo, TaskMapper mapper, TagRepo tagRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.tagRepo = tagRepo;
    }

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
            if(taskPostDto.getTagId() != null){
                Tag tag = getTagRepo().findById(taskPostDto.getTagId()).get();
                task.setTag(tag);
            }
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
    public List<TaskDto> sort(SortDto sortDto) {
        Pageable pageable = PageRequest.of(sortDto.getPageNumber()-1,20);
        List<Task> tasks = getRepo().findTasksByPage(sortDto.getUserId(),pageable);
        SortBase base = sortDto.getBase();
        SortType type = sortDto.getType();
        if (base == SortBase.DEADLINE){
             tasks.sort(Comparator.comparing(Task::getDeadline));
        }
        else if (base == SortBase.ALPHAPETICALLY){
            tasks.sort(Comparator.comparing(Task::getDescription,String.CASE_INSENSITIVE_ORDER));
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
    public List<TaskDto> findByTag(Long tagId) {
        List<Task> tasks = getRepo().findTasksByTagId(tagId);
        return tasks.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void printNotification(){
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = getRepo().findAll();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()){
            Task task = iterator.next();
            if (task.getDeadline().isBefore(now)){
                System.out.println("(User : "+task.getUser().getId()+" ) "+"(Task : "+task.getId()+" ) "+task.getDescription());
            }
        }
    }
}
