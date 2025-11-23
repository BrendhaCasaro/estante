package com.brendha.estante.controller;

import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);

        URI location = URI.create("/users?id=" + savedUser.getId());

        return ResponseEntity.created(location).body(savedUser);

        // retornar algum status code para quando o user ja existe
    }

    @GetMapping
    public ResponseEntity<User> findUserById(@RequestParam Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUserById(@RequestParam Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam Integer id, @RequestBody User user) {
        userService.updateUserById(id, user);
        return ResponseEntity.ok().build();
    }
}
