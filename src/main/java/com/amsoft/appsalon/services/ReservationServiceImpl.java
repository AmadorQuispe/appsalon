package com.amsoft.appsalon.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.amsoft.appsalon.models.Reservation;
import com.amsoft.appsalon.repositories.ReservationRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ReservationServiceImpl implements ReservationService {
    @Inject
    private ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> save(Reservation reservation) {

        return Optional.ofNullable(reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getByReservedDate(LocalDate resDate) {
        return reservationRepository.findByReservedDate(resDate);
    }

    @Override
    public Boolean updateStatus(Long reservationId, String status) {
        try {
            reservationRepository.updateStatus(reservationId, status);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
