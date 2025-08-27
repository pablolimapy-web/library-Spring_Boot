package com.library.domain.repository;

import com.library.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Buscar livros pelo título
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Buscar livros disponíveis
    List<Book> findByAvailableTrue();
}
