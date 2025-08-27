package com.library.domain.repository;

import com.library.domain.model.Loan;
import com.library.domain.model.User;
import com.library.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    // Buscar todos os empréstimos ativos (não devolvidos)
    List<Loan> findByReturnDateIsNull();

    // Buscar empréstimos de um usuário específico
    List<Loan> findByUserAndReturnDateIsNull(User user);

    // Buscar empréstimos de um livro específico
    List<Loan> findByBookAndReturnDateIsNull(Book book);
}
