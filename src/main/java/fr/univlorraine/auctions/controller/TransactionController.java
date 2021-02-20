/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.controller;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Transactions;
import fr.univlorraine.auctions.model.TransactionsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author awais
 */
@Named("transactionController")
@SessionScoped
public class TransactionController implements Serializable {

     @EJB
    private TransactionsFacadeLocal transactionFacade;

    public TransactionController() {

    }

    public List<Transactions> findAll() {

        AppUser user = (AppUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return transactionFacade.findAllUserId(user.getId());

    }
}

