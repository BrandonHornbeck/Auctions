/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.model;

import fr.univlorraine.auctions.entities.Transactions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> implements TransactionsFacadeLocal {

    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }
    
}
