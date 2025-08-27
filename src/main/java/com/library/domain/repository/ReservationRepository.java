package com.library.domain.repository;

import com.library.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserIdAndActiveTrue(UUID userId);
    List<Reservation> findByBookIdAndActiveTrue(UUID bookId);
}
