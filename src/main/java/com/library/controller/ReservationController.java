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

    // Criar reserva via JSON
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO dto) {
        try {
            Reservation reservation = reservationService.createReservation(dto.getBookId(), dto.getUserId());
            return ResponseEntity.ok(ReservationMapper.toDTO(reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar reservas ativas
    @GetMapping("/active")
    public List<ReservationDTO> getActiveReservations() {
        return reservationService.getActiveReservations()
                .stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable("id") UUID id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok("Reservation canceled successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
