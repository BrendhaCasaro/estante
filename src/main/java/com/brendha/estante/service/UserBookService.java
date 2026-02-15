package com.brendha.estante.service;

import com.brendha.estante.infrastructure.entities.Book;
import com.brendha.estante.infrastructure.entities.User;
import com.brendha.estante.infrastructure.repository.BookRepository;
import com.brendha.estante.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBookService {
    final private BookRepository bookRepository;
    final private UserRepository userRepository;

    public void addBookToUser(Integer bookId, Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow();

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Book book = bookOpt.orElseThrow();

        user.getBooks().add(book);

        userRepository.save(user);

    }

    public void removeBookFromUser(Integer bookId, Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow();

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Book book = bookOpt.orElseThrow();

        user.getBooks().remove(book);
        userRepository.save(user);
    }

    public List<Book> listAllBooksFromUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow();

        return user.getBooks();
    }

}
