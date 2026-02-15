package com.brendha.estante.controller;

import com.brendha.estante.controller.dto.UserBookRequest;
import com.brendha.estante.infrastructure.entities.Book;
import com.brendha.estante.service.UserBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class UserBookController {
    private final UserBookService userBookService;

        public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping
    public ResponseEntity<?> addUserToBook(@RequestBody UserBookRequest request) {
        try {
            userBookService.addBookToUser(request.bookId(), request.userId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Book not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserFromBook(@RequestBody UserBookRequest request) {
        try {
            userBookService.removeBookFromUser(request.bookId(), request.userId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Book not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> getBooksFromUser(@RequestParam Integer userId) {
        try {
            List<Book> books = userBookService.listAllBooksFromUser(userId);
            return ResponseEntity.ok().body(books);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exist");
        }
    }

}
