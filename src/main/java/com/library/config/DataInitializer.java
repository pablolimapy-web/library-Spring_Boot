package com.library.config;

import com.library.domain.model.Book;
import com.library.domain.model.User;
import com.library.domain.service.BookService;
import com.library.domain.service.UserService;
import com.library.domain.service.LoanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookService bookService;
    private final UserService userService;
    private final LoanService loanService;

    public DataInitializer(BookService bookService, UserService userService, LoanService loanService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar usuários iniciais
        if (userService.getAllUsers().isEmpty()) {
            User user1 = new User(null, "Alice", "alice@example.com");
            User user2 = new User(null, "Bob", "bob@example.com");
            userService.saveUser(user1);
            userService.saveUser(user2);
        }

        // Criar livros iniciais
        if (bookService.getAllBooks().isEmpty()) {
            Book book1 = new Book(null, "Clean Code", "Robert C. Martin", true);
            Book book2 = new Book(null, "Effective Java", "Joshua Bloch", true);
            Book book3 = new Book(null, "Spring in Action", "Craig Walls", true);
            bookService.saveBook(book1);
            bookService.saveBook(book2);
            bookService.saveBook(book3);
        }

        // (Opcional) Criar empréstimos iniciais
        // LoanService pode ser usado aqui se quiser testar empréstimos ativos
    }
}
