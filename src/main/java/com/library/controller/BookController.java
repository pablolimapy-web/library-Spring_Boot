package com.library.controller;

import com.library.domain.model.Book;
import com.library.domain.service.BookService;
import com.library.application.dto.BookDTO;
import com.library.application.mapper.BookMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /books → retorna todos os livros
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /books/available → retorna apenas livros disponíveis
    @GetMapping("/available")
    public List<BookDTO> getAvailableBooks() {
        return bookService.getAvailableBooks()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") UUID id) {
        return bookService.getBookById(id)
                .map(BookMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /books → cria um novo livro
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookService.saveBook(book);
        return BookMapper.toDTO(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") UUID id, @RequestBody BookDTO bookDTO) {
        return bookService.getBookById(id)
                .map(existing -> {
                    existing.setTitle(bookDTO.getTitle());
                    existing.setAuthor(bookDTO.getAuthor());
                    existing.setAvailable(bookDTO.isAvailable());
                    Book updated = bookService.saveBook(existing);
                    return ResponseEntity.ok(BookMapper.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /books/{id} → deleta um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.getBookById(id).ifPresent(book -> bookService.saveBook(book)); // opcional: verificar regras
        bookService.getBookById(id).ifPresent(book -> bookService.saveBook(book));
        bookService.getBookById(id).ifPresent(book -> bookService.saveBook(book));
        bookService.getBookById(id).ifPresent(book -> bookService.saveBook(book));
        return ResponseEntity.noContent().build();
    }
}
