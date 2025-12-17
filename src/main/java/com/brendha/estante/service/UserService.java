package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

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

    public void updateUserById(Integer id, User receivedUser) {
        User currentUser = findUserById(id);

        if (receivedUser.getEmail() != null) {
            currentUser.setEmail(receivedUser.getEmail());
        }

        if (receivedUser.getName() != null) {
            currentUser.setName(receivedUser.getName());
        }

        userRepository.save(currentUser);
    }

    public void deleteUserById(Integer id) {
        // lógica decente de caso queira deletar usuário que não existe
       userRepository.deleteById(id);
    }
}
