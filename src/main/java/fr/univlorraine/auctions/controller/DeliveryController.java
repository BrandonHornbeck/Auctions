/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.controller;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import fr.univlorraine.auctions.model.DeliveryFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author awais
 */
@Named("deliveryController")
@SessionScoped
public class DeliveryController implements Serializable {

    @EJB
    private DeliveryFacadeLocal deliveryFacade;

    List<Item> productList = new ArrayList<>();

    public DeliveryController() {

    }

    public List<Item> findAll() {

        AppUser user = (AppUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return deliveryFacade.findAllUserId(user.getId());

    }

}
