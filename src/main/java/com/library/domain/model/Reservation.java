package com.library.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private boolean active;

    public Reservation() {}

    public Reservation(UUID id, Book book, User user, LocalDate reservationDate, boolean active) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.reservationDate = reservationDate;
        this.active = active;
    }

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
