package com.pm.project.service;

import com.pm.project.entity.User;
import com.pm.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID doRegistration(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return null;
        }
        userRepository.save(user);
        return user.getId();
    }
}
