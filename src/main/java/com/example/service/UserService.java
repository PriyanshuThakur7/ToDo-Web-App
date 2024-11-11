package com.example.service;

import com.example.entity.ToDoUser;
import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public boolean userExists(String username) {
        ToDoUser user=repo.findByUsername(username);
        return user != null;
    }

    public void registerNewUser(ToDoUser user) {
        repo.save(user);
    }

    public boolean login(String username, String password) {
        ToDoUser user = repo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public ToDoUser findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
