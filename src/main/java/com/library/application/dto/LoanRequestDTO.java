package com.library.application.dto;

import java.util.UUID;

public class LoanRequestDTO {
    private UUID bookId;
    private UUID userId;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
