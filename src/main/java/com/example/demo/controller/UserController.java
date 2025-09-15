package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/update-email")
    public User updateEmailUser(@RequestBody User user) {
       
        List<User> users = userRepository.findByFirstName(user.getFirstName());

        if (users.isEmpty()) {
            throw new RuntimeException("User with first name '" + user.getFirstName() + "' not found.");
        }

  
        User foundUser = users.get(0);
        foundUser.setEmail(user.getEmail());

        return userRepository.save(foundUser);
    }
}
