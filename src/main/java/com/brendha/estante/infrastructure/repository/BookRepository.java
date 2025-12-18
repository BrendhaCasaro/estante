package com.brendha.estante.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brendha.estante.infrastructure.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {}
