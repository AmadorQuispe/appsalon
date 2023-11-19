package com.amsoft.appsalon.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.amsoft.appsalon.models.Reservation;

public interface ReservationService {
    Optional<Reservation> save(Reservation reservation);

    List<Reservation> getAll();

    List<Reservation> getByReservedDate(LocalDate resDate);

    Boolean updateStatus(Long reservationId, String status);
}
