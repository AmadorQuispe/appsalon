package com.amsoft.appsalon.repositories;

import com.amsoft.appsalon.models.Service;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    void inactiveService(Long serviceId);
}
