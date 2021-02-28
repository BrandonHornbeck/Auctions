/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oz
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "acknowledgeMode",
        propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(
        propertyName = "destinationType",
        propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(
        propertyName = "destinationLookup",
        propertyValue = "jms/OrderQueue"),
})
public class DeliveryBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext context;

    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;
    
    @Override
    public void onMessage(Message msg) {
        try {
            Order o = msg.getBody(Order.class);
            
            for(Item i : o.getItems()) {
                i.setDelivered(true);
                em.merge(i);
            }
            System.out.println("Delivering: " + o.toString());
        } catch (JMSException ex) {
            ex.printStackTrace();
            context.setRollbackOnly();
        }
    }
    
}
