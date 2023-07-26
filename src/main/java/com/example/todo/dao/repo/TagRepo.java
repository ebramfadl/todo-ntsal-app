package com.example.todo.dao.repo;

import com.example.todo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag,Long> {

}
