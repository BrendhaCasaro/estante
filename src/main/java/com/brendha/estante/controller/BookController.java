package com.brendha.estante.controller;

import com.brendha.estante.infrastructure.entities.Book;
import com.brendha.estante.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook;
        try {
            savedBook = bookService.createBook(book);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create("/books?id=" + savedBook.getId());
        return ResponseEntity.created(location).body(savedBook);
    }

    @GetMapping
    public ResponseEntity<Book> findBookById(@RequestParam Integer id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestParam Integer id, @RequestBody Book book) {
        bookService.updateBookById(id, book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Book> deleteBook(@RequestParam Integer id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
