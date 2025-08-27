package com.library.application.mapper;

import com.library.application.dto.ReservationDTO;
import com.library.domain.model.Reservation;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setBookId(reservation.getBook().getId());
        dto.setUserId(reservation.getUser().getId());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setActive(reservation.isActive());
        return dto;
    }
}
