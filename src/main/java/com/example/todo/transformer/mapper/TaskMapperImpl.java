package com.example.todo.transformer.mapper;

import com.example.todo.dao.repo.SystemUserRepo;
import com.example.todo.dao.repo.TagRepo;
import com.example.todo.dao.repo.TaskRepo;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TaskPostDto;
import com.example.todo.exception.ApiRequestException;
import com.example.todo.model.Tag;
import com.example.todo.model.TodoList;
import com.example.todo.model.SystemUser;
import com.example.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Data
public class TaskMapperImpl implements TaskMapper{

    @Autowired
    private final SystemUserRepo systemUserRepo;
    @Autowired
    private final TaskRepo taskRepo;
    @Autowired
    private final TagRepo tagRepo;

    public TaskMapperImpl(SystemUserRepo systemUserRepo, TaskRepo taskRepo, TagRepo tagRepo) {
        this.systemUserRepo = systemUserRepo;
        this.taskRepo = taskRepo;
        this.tagRepo = tagRepo;
    }


    @Override
    public TaskDto entityToDto(Task task) {
        String categoryTitle = null;
        if(task.getTodoList() != null)
            categoryTitle = task.getTodoList().getTitle();

        return new TaskDto(task.getTag().getTagName(),task.getDescription(),task.getDeadline(),task.getDateCreated(),task.getLastModifiedDate(),task.getPriority(),task.getStatus(),categoryTitle);
    }

    @Override
    public Task dtoToEntity(TaskPostDto taskPostDto) {
        Optional<SystemUser> optional = getSystemUserRepo().findById(taskPostDto.getUserId());

        if (taskPostDto.getDeadline() == null){
            throw new ApiRequestException("You need to provide the dedline of the task!");
        }
        if(!optional.isPresent()){
            throw new ApiRequestException("User does not exist!");
        }
        SystemUser user = optional.get();
        TodoList todoList = getTaskRepo().findUserCategory(taskPostDto.getCategoryId(),taskPostDto.getUserId());

        if(todoList == null && taskPostDto.getCategoryId() != null){
            throw new ApiRequestException("Category does not exist!");
        }
        if(taskPostDto.getDeadline().isBefore(LocalDateTime.now())){
            throw new ApiRequestException("Deadline has to be after the current date!");
        }
        Tag tag = getTagRepo().findById(taskPostDto.getTagId()).get();
        return new Task(tag,taskPostDto.getDescription(),taskPostDto.getDeadline(),taskPostDto.getPriority(),user, todoList);
    }
}
