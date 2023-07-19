package com.example.todo.dao.repo;


import com.example.todo.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser,Long> {
}
