package com.amsoft.appsalon.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
@SequenceGenerator(name = "services_sequence", sequenceName = "services_sequence", allocationSize = 1)
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_sequence")
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String statusReg;

    public Service() {
    }

    public Service(Long id, String name, String description, Double price, String statusReg) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.statusReg = statusReg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatusReg() {
        return statusReg;
    }

    public void setStatusReg(String statusReg) {
        this.statusReg = statusReg;
    }

}
