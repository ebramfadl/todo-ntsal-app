package com.example.todo.dao.repo;

import com.example.todo.enums.TaskStatus;
import com.example.todo.model.TodoList;
import com.example.todo.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findTasksByUserId(Long userId);

    @Query("SELECT c FROM TodoList c WHERE c.id = :categoryId AND c.user.id = :userId")
    TodoList findUserCategory(Long categoryId, Long userId);

    @Query("SELECT t FROM Task t WHERE t.status = :status AND t.user.id = :userId AND t.dateCompleted >= :oneMonthAgo")
    List<Task> findCompletedTasksByStatusAndUserId(TaskStatus status, Long userId,LocalDateTime oneMonthAgo);

    List<Task> findTasksByStatusAndUserId(TaskStatus status, Long userId);

    List<Task> findByDeadlineBetweenAndUserId(LocalDateTime startOfDay, LocalDateTime endOfDay,Long userId);

    List<Task> findByDescriptionContainingIgnoreCaseAndUserId(String keyword,Long userId);

    List<Task> findTasksByTagId(Long tagId);

    @Query("SELECT t FROM Task t  WHERE t.user.id =: userId")
    List<Task> findTasksByPage(Long userId, Pageable pageable);

}
