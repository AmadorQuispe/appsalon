package com.amsoft.appsalon.services;

import java.util.List;
import java.util.Optional;

import com.amsoft.appsalon.models.Service;
import com.amsoft.appsalon.repositories.ServiceRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ServiceServiceImpl implements ServiceService {
    @Inject
    private ServiceRepository serviceRepo;

    @Override
    public void save(Service service) {
        serviceRepo.save(service);
    }

    @Override
    public List<Service> getAll() {
        return serviceRepo.findAll();
    }

    @Override
    public Optional<Service> getById(Long id) {
        Service service = serviceRepo.findById(id);
        return Optional.ofNullable(service);
    }

    @Override
    public boolean inactiveService(Long serviceId) {
        try {
            serviceRepo.inactiveService(serviceId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
