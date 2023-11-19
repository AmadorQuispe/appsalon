package com.amsoft.appsalon.controllers;

import java.time.LocalDate;
import java.util.List;

import com.amsoft.appsalon.models.Reservation;
import com.amsoft.appsalon.services.ReservationService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class AdminController {
    @Inject
    private ReservationService reservationService;
    private LocalDate reservedDate;
    private List<Reservation> allReservationsFiltered;

    @PostConstruct
    private void init() {
        allReservationsFiltered = reservationService.getAll();
        if (allReservationsFiltered.size() <= 0) {

        }
    }

    public String filterReservationsByDate() {
        if (reservedDate != null) {
            allReservationsFiltered = reservationService.getByReservedDate(reservedDate);
        }
        return "";
    }

    public String markFinished(Long reservationId) {
        Boolean updated = reservationService.updateStatus(reservationId, "F");
        if (updated) {
            this.allReservationsFiltered = allReservationsFiltered.stream()
                    .filter(reservation -> reservation.getId() != reservationId).toList();
        }

        return "";
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public List<Reservation> getAllReservationsFiltered() {
        return allReservationsFiltered;
    }

    public void setAllReservationsFiltered(List<Reservation> allReservationsFiltered) {
        this.allReservationsFiltered = allReservationsFiltered;
    }

}
