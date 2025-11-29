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
        if (userRepository.existsByEmail(user.getEmail()).isPresent()) {
            // retornar uma exception de usuário ja existente.
            return user;
            // terminar logica de existencia do usuario.
            // Precisa saber o que retornar para que st 500 não seja enviado na req
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

        User updatedUser = User.builder()
                .email(receivedUser.getEmail() != null ? receivedUser.getEmail() : currentUser.getEmail())
                .name(receivedUser.getName() != null ? receivedUser.getName() : currentUser.getName())
                .id(receivedUser.getId())
                .build();

        userRepository.save(updatedUser);
    }

    public void deleteUserById(Integer id) {
       userRepository.deleteById(id);
    }
}
