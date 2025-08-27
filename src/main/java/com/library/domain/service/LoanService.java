package com.library.domain.service;

import com.library.domain.model.Book;
import com.library.domain.model.Loan;
import com.library.domain.model.User;
import com.library.domain.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final UserService userService;

    public LoanService(LoanRepository loanRepository, BookService bookService, UserService userService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public Loan createLoan(UUID bookId, UUID userId) throws IllegalArgumentException {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (!book.isAvailable()) {
            throw new IllegalArgumentException("Book is currently not available");
        }

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null);

        Loan savedLoan = loanRepository.save(loan);

        bookService.setBookAvailability(bookId, false);

        return savedLoan;
    }

    public Loan returnBook(UUID loanId) throws IllegalArgumentException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new IllegalArgumentException("Book already returned");
        }

        loan.setReturnDate(LocalDate.now());
        Loan returnedLoan = loanRepository.save(loan);

        bookService.setBookAvailability(loan.getBook().getId(), true);

        return returnedLoan;
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    public List<Loan> getActiveLoansByUser(UUID userId) throws IllegalArgumentException {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return loanRepository.findByUserAndReturnDateIsNull(user);
    }

    public List<Loan> getActiveLoansByBook(UUID bookId) throws IllegalArgumentException {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        return loanRepository.findByBookAndReturnDateIsNull(book);
    }
}
