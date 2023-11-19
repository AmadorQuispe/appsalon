package com.amsoft.appsalon.repositories;

import java.time.LocalDate;
import java.util.List;

import com.amsoft.appsalon.models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByReservedDate(LocalDate resDate);

    void updateStatus(Long reservationId, String status);
}
