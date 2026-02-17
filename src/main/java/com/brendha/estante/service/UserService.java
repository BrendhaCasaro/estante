package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        userRepository.saveAndFlush(user);
        return user;
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    public User updateUserById(Integer id, User receivedUser) {
        User currentUser = findUserById(id);

        if (receivedUser.getEmail() != null) {
            currentUser.setEmail(receivedUser.getEmail());
        }

        if (receivedUser.getName() != null) {
            currentUser.setName(receivedUser.getName());
        }

        return userRepository.save(currentUser);
    }

    public User deleteUserById(Integer id) {
        User currentUser = findUserById(id);
        userRepository.deleteById(id);
        return currentUser;
    }
}
