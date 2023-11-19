package com.amsoft.appsalon.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "reservations")
@SequenceGenerator(name = "reservations_sequence", sequenceName = "reservations_sequence", allocationSize = 1)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_sequence")
    private Long id;
    private LocalDate reservedDate;
    private LocalTime reservedTime;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;
    private String statusReg;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "userUid")
    private User user;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "reservationServices", joinColumns = @JoinColumn(name = "reservationId", nullable = false), inverseJoinColumns = @JoinColumn(name = "serviceId", nullable = false), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "reservationId", "serviceId" }) })
    private Set<Service> services;

    public Reservation() {
    }

    public void addService(Service service) {
        if (services == null) {
            services = new HashSet<>();
        }
        services.add(service);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public LocalTime getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(LocalTime reservedTime) {
        this.reservedTime = reservedTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatusReg() {
        return statusReg;
    }

    public void setStatusReg(String statusReg) {
        this.statusReg = statusReg;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

}
