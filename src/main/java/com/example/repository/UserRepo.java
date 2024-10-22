package com.example.repository;

import com.example.entity.ToDoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<ToDoUser,Long> {
    ToDoUser findByUsername(String username);
}
