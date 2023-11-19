package com.amsoft.appsalon.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.amsoft.appsalon.models.Service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class WizardBean implements Serializable {
    @Inject
    private Conversation conversation;
    private LocalDate reservedDate;
    private String reservedTime;
    private List<Service> services;
    private String currentStep = "services";

    @PostConstruct
    private void init() {
        this.services = new ArrayList<Service>();
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public boolean getShowPrevious() {
        return !"services".equals(currentStep);
    }

    public boolean getShowNext() {
        return !"summary".equals(currentStep);
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void nextStep() {
        if ("services".equals(currentStep)) {
            currentStep = "reserved";
        } else if ("reserved".equals(currentStep)) {
            currentStep = "summary";
        }
    }

    public void previousStep() {
        if ("reserved".equals(currentStep)) {
            currentStep = "services";
        } else if ("summary".equals(currentStep)) {
            currentStep = "reserved";
        }
    }

    public void toggleServiceSelection(Service s) {
        Long serviceId = s.getId();
        if (services.stream().map(Service::getId).anyMatch(id -> id == serviceId)) {
            Iterator<Service> iterator = services.iterator();
            while (iterator.hasNext()) {
                Service service = iterator.next();
                if (service.getId() == serviceId) {
                    iterator.remove();
                    break;
                }
            }
        } else {
            services.add(s);
        }
    }

    public boolean isServiceSelected(Service s) {
        Long serviceId = s.getId();
        return services.stream().map(Service::getId).anyMatch(id -> id == serviceId);
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(String reservedTime) {
        this.reservedTime = reservedTime;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
