/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.model;

import fr.univlorraine.auctions.entities.Paymentmethod;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp
 */
@Stateless
public class PaymentmethodFacade extends AbstractFacade<Paymentmethod> implements PaymentmethodFacadeLocal {

    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentmethodFacade() {
        super(Paymentmethod.class);
    }
    
}
