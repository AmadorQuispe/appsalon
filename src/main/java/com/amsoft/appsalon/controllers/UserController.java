package com.amsoft.appsalon.controllers;

import com.amsoft.appsalon.models.User;
import com.amsoft.appsalon.services.UserService;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@Model
public class UserController {
    @Inject
    private UserBean userBean;

    @Inject
    private UserService userService;

    private boolean successRegister = false;

    public String saveUser() {
        User user = new User();
        user.setFirstName(this.userBean.getFirstName());
        user.setLastName(this.userBean.getLastName());
        user.setEmail(this.userBean.getEmail());
        user.setPhone(this.userBean.getPhone());
        user.setPassword(this.userBean.getPassword());
        try {
            this.userService.save(user);
            this.emptyVariables();
            this.successRegister = true;
            return "";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "email",
                    "Ya existe cuenta con el correo");
            FacesContext.getCurrentInstance().addMessage("formRegister:email", message);
            return "";
        }

    }

    private void emptyVariables() {
        this.userBean = new UserBean();
    }

    public void setSelectedUser(User selectedUser) {
        // this.selectedUser = selectedUser;
    }

    public boolean isSuccessRegister() {
        return successRegister;
    }

    public void setSuccessRegister(boolean successRegister) {
        this.successRegister = successRegister;
    }

}
