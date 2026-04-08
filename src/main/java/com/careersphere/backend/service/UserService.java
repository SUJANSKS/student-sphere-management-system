package com.careersphere.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careersphere.backend.model.User;
import com.careersphere.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {
        return repository.save(user);
    }

    public User login(String email, String password) {
        User user = repository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // ✅ FIX: ADD THIS METHOD
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}