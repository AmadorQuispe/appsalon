package com.amsoft.appsalon.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Named
@RequestScoped
public class ServiceBean {
    private Long id;
    @NotBlank(message = "Este campo es obligatorio")
    private String name;
    @NotBlank(message = "Este campo es obligatorio")
    private String description;
    @Min(value = 0, message = "Debe ser mayor a cero")
    @NotNull(message = "Este campo es obligatorio")
    private Double price;

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

}
