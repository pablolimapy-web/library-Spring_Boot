package com.library.domain.service;

import com.library.domain.model.Book;
import com.library.domain.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Injeção de dependência via construtor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Criar ou atualizar um livro
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Buscar todos os livros
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Buscar livro por ID
    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    // Buscar livros disponíveis
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    // Buscar livros por título
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Marcar livro como disponível ou emprestado
    public void setBookAvailability(UUID bookId, boolean available) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAvailable(available);
            bookRepository.save(book);
        }
    }
}
