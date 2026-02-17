package com.brendha.estante.controller;

import com.brendha.estante.controller.dto.BookResponse;
import com.brendha.estante.infrastructure.entities.Book;
import com.brendha.estante.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody Book book) {
        Book savedBook;
        try {
            savedBook = bookService.createBook(book);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create("/books?id=" + savedBook.getId());
        BookResponse response = new BookResponse(savedBook.getId(), savedBook.getName(), savedBook.getAuthor());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findBookById(@RequestParam Integer id) {
        try {
            Book savedBook = bookService.findBookById(id);
            BookResponse response = new BookResponse(savedBook.getId(), savedBook.getName(), savedBook.getAuthor());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestParam Integer id, @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBookById(id, book);
            BookResponse response = new BookResponse(updatedBook.getId(), updatedBook.getName(), updatedBook.getAuthor());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestParam Integer id) {
        try {
            Book deletedBook = bookService.deleteBookById(id);
            BookResponse response = new BookResponse(deletedBook.getId(), deletedBook.getName(), deletedBook.getAuthor());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }
}
