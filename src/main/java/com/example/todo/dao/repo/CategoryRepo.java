package com.example.todo.dao.repo;

import com.example.todo.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<TodoList,Long> {

    List<TodoList> findCategoriesByUserId(Long userId);
}
