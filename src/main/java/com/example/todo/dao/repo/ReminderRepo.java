package com.example.todo.dao.repo;

import com.example.todo.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder,Long> {

    @Query("select r from Reminder r where r.task.user.id = :userId")
    List<Reminder> findUserReminders(Long userId);
}
