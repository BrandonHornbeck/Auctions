/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.model;

import fr.univlorraine.auctions.entities.Paymentmethod;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp
 */
@Local
public interface PaymentmethodFacadeLocal {

    void create(Paymentmethod paymentmethod);

    void edit(Paymentmethod paymentmethod);

    void remove(Paymentmethod paymentmethod);

    Paymentmethod find(Object id);

    List<Paymentmethod> findAll();

    List<Paymentmethod> findRange(int[] range);

    int count();
    
}
