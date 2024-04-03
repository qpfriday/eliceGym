package org.example.backend.service;

import org.example.backend.entity.User;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // Method to fetch user's name by ID
    public String getNameById(int userId) {
        // Assuming your UserRepository has a method to fetch user by ID
        // Replace 'User' with your actual user entity class name
        User user = userRepository.findById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return null; // or throw an exception if user not found
        }
    }
}
