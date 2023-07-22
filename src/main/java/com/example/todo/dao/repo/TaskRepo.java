package com.example.todo.dao.repo;

import com.example.todo.enums.TaskStatus;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findTasksByUserId(Long userId);

    @Query("select c from Category c where exists (select t from Task t where t.category.id = :categoryId and t.user.id = :userId and c.id=:categoryId )")
    Category findUserCategory(Long categoryId, Long userId);

    List<Task> findTasksByStatusAndUserId(TaskStatus status, Long userId);
    List<Task> findByDeadlineBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Task> findByDescriptionContainingIgnoreCase(String keyword);

}
