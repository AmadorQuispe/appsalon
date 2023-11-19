package com.amsoft.appsalon.controllers;

import java.time.LocalTime;

import com.amsoft.appsalon.models.Reservation;
import com.amsoft.appsalon.models.Service;
import com.amsoft.appsalon.models.User;
import com.amsoft.appsalon.services.ReservationService;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class ReservationController {
    @Inject
    private ReservationService reservationService;
    @Inject
    private WizardBean reservationBean;
    @Inject
    private AuthBean authBean;

    public String saveReservation() {
        Reservation reservation = new Reservation();
        User user = new User();
        user.setUid(authBean.getUserUid());
        reservation.setUser(user);
        reservation.setReservedDate(reservationBean.getReservedDate());
        reservation.setReservedTime(LocalTime.parse(reservationBean.getReservedTime()));
        reservation.setStatusReg("A");
        for (Service service : reservationBean.getServices()) {
            reservation.addService(service);
        }
        try {
            reservationService.save(reservation);
            reservationBean.endConversation();
        } catch (Exception e) {
            return "";
        }

        return "/index.xhtml?faces-redirect=true";
    }

}
