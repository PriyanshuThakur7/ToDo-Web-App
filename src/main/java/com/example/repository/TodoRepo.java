package com.example.repository;

 
import com.example.entity.ToDoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ToDo;

import java.util.List;
import java.util.Optional;


@Repository
public interface TodoRepo extends JpaRepository<ToDo,Integer> {

    void deleteById(Long id);

    Optional<ToDo> findById(Long id);

    List<ToDo> findByUserAndStatus(ToDoUser user, boolean status);
}
