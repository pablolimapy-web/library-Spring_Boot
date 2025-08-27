package com.library.application.mapper;

import com.library.application.dto.ReservationDTO;
import com.library.domain.model.Reservation;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) return null;

        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setActive(reservation.isActive());

        // Tratamento seguro para book e user
        if (reservation.getBook() != null) {
            dto.setBookId(reservation.getBook().getId());
        }

        if (reservation.getUser() != null) {
            dto.setUserId(reservation.getUser().getId());
        }

        return dto;
    }
}
