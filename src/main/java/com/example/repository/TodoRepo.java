package com.example.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ToDo;



@Repository
public interface TodoRepo extends JpaRepository<ToDo,Integer> {

}
