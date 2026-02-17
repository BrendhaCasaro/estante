package com.brendha.estante.controller;

import com.brendha.estante.controller.dto.UserResponse;
import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        User savedUser;
        try {
            savedUser = userService.createUser(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create("/users?id=" + savedUser.getId());

        UserResponse response = new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findUserById(@RequestParam Integer id) {
        User savedUser;
        try {
           savedUser = userService.findUserById(id);
           UserResponse response = new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
           return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestParam Integer id) {
        try {
            User deletedUser = userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestParam Integer id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUserById(id, user);
            UserResponse response = new UserResponse(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getName());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
