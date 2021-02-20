/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.controller;

import fr.univlorraine.auctions.entities.AppUser;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author awais
 */
@Named("dashboardController")
@SessionScoped
public class DashboardController implements Serializable {

    public DashboardController() {
        user = (AppUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    AppUser user = new AppUser();

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
    

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
