package com.amsoft.appsalon.services;

import java.util.List;
import java.util.Optional;

import com.amsoft.appsalon.models.Service;

public interface ServiceService {
    List<Service> getAll();

    Optional<Service> getById(Long id);

    boolean inactiveService(Long serviceId);

    void save(Service service);
}
