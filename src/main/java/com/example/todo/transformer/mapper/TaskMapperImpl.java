package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.CategoryRepo;
import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.enums.TaskStatus;
import com.example.todo.model.Category;
import com.example.todo.model.SystemUser;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TaskMapperImpl implements TaskMapper{

    @Autowired
    private SystemUserRepo systemUserRepo;
    @Autowired
    private TaskRepo taskRepo;

    public TaskMapperImpl(SystemUserRepo systemUserRepo, TaskRepo taskRepo) {
        this.systemUserRepo = systemUserRepo;
        this.taskRepo = taskRepo;
    }

    public SystemUserRepo getSystemUserRepo() {
        return systemUserRepo;
    }

    public TaskRepo getTaskRepo() {
        return taskRepo;
    }

    @Override
    public TaskDto entityToDto(Task task) {
        String categoryTitle = null;
        if(task.getCategory() != null)
            categoryTitle = task.getCategory().getTitle();

        return new TaskDto(task.getTag(),task.getDescription(),task.getDeadline(),task.getDateCreated(),task.getLastModifiedDate(),task.getPriority(),task.getStatus(),categoryTitle);
    }

    @Override
    public Task dtoToEntity(TaskPostDto taskPostDto) {
        Optional<SystemUser> optional = getSystemUserRepo().findById(taskPostDto.getUserId());

        if (taskPostDto.getDeadline() == null){
            throw new IllegalStateException("You need to provide the dedline of the task!");
        }
        if(!optional.isPresent()){
            throw new IllegalStateException("User does not exist!");
        }
        SystemUser user = optional.get();
        Category category = getTaskRepo().findUserCategory(taskPostDto.getCategoryId(),taskPostDto.getUserId());

        if(category == null && taskPostDto.getCategoryId() != null){
            throw new IllegalStateException("Category does not exist!");
        }
        if(taskPostDto.getDeadline().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Deadline has to be after the current date!");
        }
        return new Task(taskPostDto.getTag(),taskPostDto.getDescription(),taskPostDto.getDeadline(),taskPostDto.getPriority(),user,category);
    }
}
