package com.library.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationDTO {
    private UUID id;
    private UUID bookId;
    private UUID userId;
    private LocalDate reservationDate;
    private boolean active;

    public ReservationDTO() {}

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBookId() { return bookId; }
    public void setBookId(UUID bookId) { this.bookId = bookId; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
