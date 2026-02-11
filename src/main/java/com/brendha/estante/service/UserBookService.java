package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.infrastructure.repository.BookRepository;
import com.brendha.estante.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBookService {
    final private BookRepository bookRepository;
    final private UserRepository userRepository;

    public void addBookToUser(Integer bookId, Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not exists");
        }
        User user = userRepository.findById(userId);

        if (!bookRepository.existsById(bookId)) {
           throw new RuntimeException("Book not exists");
        }
        Book book = bookRepository.findById(bookId);

        user.getBooks().add(book);

        userRepository.save(user);

    }

}
