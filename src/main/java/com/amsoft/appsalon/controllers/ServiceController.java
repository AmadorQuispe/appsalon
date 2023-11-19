package com.amsoft.appsalon.controllers;

import java.util.List;

import com.amsoft.appsalon.models.Service;
import com.amsoft.appsalon.services.ServiceService;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class ServiceController {
    @Inject
    private ServiceService svcService;
    @Inject
    private ServiceBean serviceBean;

    public List<Service> allServices() {
        return svcService.getAll();
    }

    public String editService(Service service) {
        serviceBean.setId(service.getId());
        serviceBean.setName(service.getName());
        serviceBean.setDescription(service.getDescription());
        serviceBean.setPrice(service.getPrice());
        return "/views/service/create.xhtml";
    }

    public String saveService() {
        Service service = new Service();
        if (serviceBean.getId() != null) {
            service.setId(serviceBean.getId());
        }
        service.setName(serviceBean.getName());
        service.setDescription(serviceBean.getDescription());
        service.setPrice(serviceBean.getPrice());
        service.setStatusReg("A");
        try {
            svcService.save(service);
        } catch (Exception e) {
            return "/views/service/index.xhtml";
        }
        this.emptyVariables();

        if (serviceBean.getId() != null) {
            this.emptyVariables();
            return "/views/service/index.xhtml?faces-redirect=true";
        } else {
            this.emptyVariables();
            return "/views/service/create.xhtml";
        }

    }

    public String inactiveService(Long serviceId) {
        try {
            svcService.inactiveService(serviceId);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }

        return "";
    }

    public void emptyVariables() {
        this.serviceBean.setId(null);
        this.serviceBean.setName("");
        this.serviceBean.setDescription("");
        this.serviceBean.setPrice(null);
    }

}
