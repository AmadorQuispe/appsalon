package com.amsoft.appsalon.controllers;

import java.io.Serializable;
import java.util.Optional;

import com.amsoft.appsalon.models.UserDetail;
import com.amsoft.appsalon.services.UserService;
import com.amsoft.appsalon.util.SessionUtils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Named
@RequestScoped
public class AuthController implements Serializable {
    @NotEmpty(message = "Ingrese su contraseña")
    private String pwd;
    @NotEmpty(message = "El usuario no puede estar vació")
    @Email(message = "Ingrese formato de correo valido")
    private String user;

    @Inject
    private UserService userService;

    @Inject
    private AuthBean authBean;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String validateUsernamePassword() {
        Optional<UserDetail> userDetail = userService.login(user, pwd);
        if (userDetail.isPresent()) {
            authBean.setUserFullName(userDetail.get().getFullName());
            authBean.setUserUid(userDetail.get().getUid());
            authBean.setEmail(userDetail.get().getEmail());
            authBean.setIsAdmin(userDetail.get().getIsAdmin());
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario o contraseña incorrecta",
                            "Please enter correct username and Password"));
            return "/views/auth/login.xhtml";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/views/auth/login.xhtml";
    }
}
