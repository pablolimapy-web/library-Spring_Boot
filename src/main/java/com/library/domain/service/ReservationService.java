package com.library.domain.service;

import com.library.domain.model.Book;
import com.library.domain.model.Reservation;
import com.library.domain.model.User;
import com.library.domain.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookService bookService;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository,
                              BookService bookService,
                              UserService userService) {
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public Reservation createReservation(UUID bookId, UUID userId) {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!book.isAvailable()) {
            throw new IllegalArgumentException("Book is not available for reservation");
        }


        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setReservationDate(LocalDate.now());
        reservation.setActive(true);

        return reservationRepository.save(reservation);
    }


    public List<Reservation> getActiveReservations() {
        return reservationRepository.findAll()
                .stream().filter(Reservation::isActive).toList();
    }

    public void cancelReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservation.setActive(false);
        reservationRepository.save(reservation);
    }
}
