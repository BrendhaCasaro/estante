package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.Book;
import com.brendha.estante.infrastructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        if (bookRepository.existsByName(book.getName())) {
            throw new RuntimeException("Book already exists");
        }

        bookRepository.saveAndFlush(book);
        return book;
    }

    public Book findBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void updateBookById(Integer id, Book receivedBook) {
        Book currentBook = findBookById(id);

        if (receivedBook.getName() != null) {
            currentBook.setName(receivedBook.getName());
        }

        if (receivedBook.getAuthor() != null) {
            currentBook.setAuthor(receivedBook.getAuthor());
        }

        bookRepository.save(currentBook);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
