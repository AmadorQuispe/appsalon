package com.amsoft.appsalon.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Named
@RequestScoped
public class UserBean {
    private String uid;
    @NotBlank(message = "Este campo es obligatorio")
    private String firstName;
    @NotBlank(message = "Este campo es obligatorio")
    private String lastName;
    @NotBlank(message = "Este campo es obligatorio")
    @Email(message = "Correo no tiene formato de valido")
    private String email;
    @NotBlank(message = "Este campo es obligatorio")
    private String phone;
    @NotBlank(message = "Este campo es obligatorio")
    @Size(min = 6, message = "Debe tener mínimo 6 caracteres")
    private String password;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
