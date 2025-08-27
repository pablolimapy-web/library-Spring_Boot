package com.library.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public class LoanDTO {

    private UUID id;
    private UUID bookId;
    private UUID userId;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanDTO() {}

    public LoanDTO(UUID id, UUID bookId, UUID userId, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBookId() { return bookId; }
    public void setBookId(UUID bookId) { this.bookId = bookId; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
