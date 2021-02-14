/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.AppUser;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.SessionMap;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author oz
 */
@Named(value = "register")
@RequestScoped
public class Register implements Serializable {

    private String status;

    private String name;
    private String login;
    private String passwd;

    @Inject
    private UserManager userManager;

    public Register() {
        status = "";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String addUser() {
        AppUser u = new AppUser(login, passwd, name);

        Long res = userManager.addUser(u);

        if (res == -1L) {
            status = "error";
        } else {
            status = "success";
        }

        return "index";
    }
}
