package com.library.controller;

import com.library.application.dto.ReservationDTO;
import com.library.application.mapper.ReservationMapper;
import com.library.domain.model.Reservation;
import com.library.domain.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestParam UUID bookId,
                                               @RequestParam UUID userId) {
        try {
            Reservation reservation = reservationService.createReservation(bookId, userId);
            return ResponseEntity.ok(ReservationMapper.toDTO(reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/active")
    public List<ReservationDTO> getActiveReservations() {
        return reservationService.getActiveReservations()
                .stream().map(ReservationMapper::toDTO).collect(Collectors.toList());
    }

    // Cancelar reserva
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable UUID id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
