package com.example.springdata.service;

import com.example.springdata.repository.UserRepository;
import com.example.springdata.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        User save = userRepository.save(user);
        return save;
    }
}
