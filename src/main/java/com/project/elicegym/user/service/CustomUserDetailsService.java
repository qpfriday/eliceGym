package com.project.elicegym.user.service;

import com.project.elicegym.user.dto.CustomUserDetails;
import com.project.elicegym.user.entity.UserEntity;
import com.project.elicegym.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByLoginId(username);
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
