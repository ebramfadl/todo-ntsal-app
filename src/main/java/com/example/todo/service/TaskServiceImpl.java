package com.example.todo.service;

import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.SortBase;
import com.example.todo.enums.SortType;
import com.example.todo.enums.TaskStatus;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.example.todo.transformer.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class TaskServiceImpl implements TaskService{

    private TaskRepo repo;
    private TaskMapper mapper;


    @Override
    public TaskDto viewTask(Long id) {
        Task task = getRepo().findById(id).get();
        if(task == null){
            throw  new IllegalStateException("Task does not exist!");
        }
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
        Task task = getRepo().findById(taskId).get();
        task.setStatus(TaskStatus.COMPLETED);
        return true;
    }

    @Override
    @Transactional
    public TaskDto deleteTask(Long taskId) {
        Task task = getRepo().findById(taskId).get();
        task.setStatus(TaskStatus.CANCELLED);
        getRepo().deleteById(taskId);
        return getMapper().entityToDto(task);
    }

    @Override
    public List<TaskDto> viewCompletedTasks(Long userId) {
        List<Task> tasks = getRepo().findTasksByStatusAndUserId(TaskStatus.COMPLETED,userId);
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
        Task task = getRepo().findById(taskId).get();
        if(task == null){
            throw new IllegalStateException("Task does not exist!");
        }
        else {
            if(taskPostDto.getTag() != null)
                task.setTag(taskPostDto.getTag());
            if (taskPostDto.getDescription() != null)
                task.setDescription(taskPostDto.getDescription());
            if (taskPostDto.getDeadline() != null)
                task.setDeadline(taskPostDto.getDeadline());
            if (taskPostDto.getStatus() != null)
                task.setStatus(taskPostDto.getStatus());
            if (taskPostDto.getRepetitionType() != null)
                task.setRepetitionType(taskPostDto.getRepetitionType());
            if (taskPostDto.getPriority() != null)
                task.setPriority(taskPostDto.getPriority());
            if (taskPostDto.getCategoryId() != null){
                Category category = getRepo().findUserCategory(taskPostDto.getCategoryId(),task.getUser().getId());
                task.setCategory(category);
            }
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


}
