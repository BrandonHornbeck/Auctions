/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oz
 */
@Stateless
public class CartManagerLocal implements CartManager {
    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;
    
    @Inject
    private JMSContext c;
    
    @Resource(lookup = "jms/OrderQueue")
    private Destination d;
    
    
    @Override
    public boolean addItemToCart(Long iid, Long uid) {
        Item i = em.find(Item.class, iid);
        AppUser u = em.find(AppUser.class, uid);
        
        if(i.getBidder().equals(u) && i.getEndDate().compareTo(LocalDateTime.now()) < 0) {
            u.getCart().add(i);
            em.merge(u);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean removeFromCart(Long iid, Long uid) {
        Item i = em.find(Item.class, iid);
        AppUser u = em.find(AppUser.class, uid);
        
        if(i.getBidder().equals(u) && i.getEndDate().compareTo(LocalDateTime.now()) < 0) {
            u.getCart().remove(i);
            em.merge(u);
            return true;
        }
        
        return false;
    }
    
    
    @Override
    public boolean orderCart(Long uid, String ccn, String address) {
        AppUser u = em.find(AppUser.class, uid);
        
        if(u != null && !u.getCart().isEmpty()) {
            for(Item i : u.getCart()) {
                i.setOrdered(true);
                em.merge(i);
            }
            
            Order o = new Order();
            o.setAddr(address);
            o.setCcn(ccn);
            o.setUid(uid);
            o.getItems().addAll(u.getCart());
            
            c.createProducer().send(d, o);
            
            u.getCart().clear();
            em.merge(u);
            return true;
        }
        
        return false;
    }
}
