package com.amsoft.appsalon.repositories;

import java.util.List;

import com.amsoft.appsalon.models.Service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@RequestScoped
public class ServiceRepositoryImpl implements ServiceRepository {
    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public Service save(Service service) {
        if (service.getId() != null) {
            return em.merge(service);
        } else {
            em.persist(service);
            return service;
        }
    }

    @Override
    public Service findById(Long id) {
        Service service = em.find(Service.class, id);
        return service;

    }

    @Override
    public List<Service> findAll() {
        return em.createQuery("Select s from Service s WHERE s.statusReg='A'", Service.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    @Transactional
    public void inactiveService(Long serviceId) {
        try {
            Query query = em.createQuery("UPDATE Service set statusReg='I' WHERE id=:serviceId");
            query.setParameter("serviceId", serviceId);
            int rowsUpdated = query.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new RuntimeException("No existe servicio con el Id: " + serviceId);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }

}
