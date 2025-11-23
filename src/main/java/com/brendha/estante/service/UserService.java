package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    public void updateUserById(Integer id, User recivedUser) {
        User currentUser = findUserById(id);

        User updatedUser = recivedUser.builder()
                .email(recivedUser.getEmail() != null ? recivedUser.getEmail() : currentUser.getEmail())
                .name(recivedUser.getName() != null ? recivedUser.getName() : currentUser.getName())
                .id(recivedUser.getId())
                .build();

        userRepository.save(updatedUser);
    }

    public void deleteUserById(Integer id) {
       userRepository.deleteById(id);
    }
}
