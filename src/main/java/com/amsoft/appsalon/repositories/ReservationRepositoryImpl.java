package com.amsoft.appsalon.repositories;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amsoft.appsalon.models.Reservation;
import com.amsoft.appsalon.models.Service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@RequestScoped
public class ReservationRepositoryImpl implements ReservationRepository {
    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public Reservation save(Reservation reservation) {

        if (reservation.getServices() != null) {
            Set<Service> managedServices = new HashSet<>();

            for (Service service : reservation.getServices()) {
                managedServices.add(entityManager.merge(service));
            }

            reservation.setServices(managedServices);
        }

        if (reservation.getId() != null) {
            reservation = entityManager.merge(reservation);
        } else {
            entityManager.persist(reservation);
        }

        return reservation;

    }

    @Override
    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("Select r from Reservation r WHERE r.statusReg='A'", Reservation.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<Reservation> findByReservedDate(LocalDate resDate) {
        try {
            TypedQuery<Reservation> query = entityManager.createQuery(
                    "select r from Reservation r where r.statusReg = 'A' AND r.reservedDate = :resDate",
                    Reservation.class);
            List<Reservation> reservation = query.setParameter("resDate", resDate).getResultList();
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void updateStatus(Long reservationId, String status) {
        try {
            Query query = entityManager.createQuery("UPDATE Reservation r SET r.statusReg = :status WHERE r.id = :id");
            query.setParameter("status", status);
            query.setParameter("id", reservationId);
            int rowsUpdated = query.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new RuntimeException("No existe reservación con el Id: " + reservationId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

}
