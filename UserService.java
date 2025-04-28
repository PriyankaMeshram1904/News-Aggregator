package com.example.news_blend.service;

import com.example.news_blend.model.User;
import com.example.news_blend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to fetch user by ID
    public Optional<User> getUserById(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty(); // Handle invalid ID cases gracefully
        }
        return userRepository.findById(id);
    }

    // Method to fetch user by username
    public User getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null; // Handle null/empty usernames gracefully
        }
        return userRepository.findByUsername(username);
    }

    // Method to update user details
    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            // Optionally, you can throw an IllegalArgumentException or return null
            return null;
        }
        return userRepository.save(user);
    }

    // Method to delete user by ID
    public void deleteUserById(Long id) {
        if (id != null && id > 0) {
            userRepository.deleteById(id);
        } else {
            // Optional: log a warning about invalid deletion attempts
            System.err.println("Invalid user ID for deletion: " + id);
        }
    }

    // Method to fetch all users
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

