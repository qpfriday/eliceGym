package org.example.backend.service;

import org.example.backend.dto.UserDto;
import org.example.backend.entity.User;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // Method to fetch user's name by ID
    public String getNameById(int userId) {
        User user = userRepository.findById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return null;
        }
    }
    public String getRoleById(int userId) {
        User user = userRepository.findById(userId);

        if (user == null) { return null; }

        return user.getRole();
    }

    public boolean isIdAlreadyExists(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }
    public void join(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setLoginId(userDto.getLoginId());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setRole(userDto.getRole());

        userRepository.save(newUser);
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }
}
