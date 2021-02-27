/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author oz
 */
@Stateless
public class ItemManagerLocal implements ItemManager {
    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;
    
    @Override
    public Item findItem(Long id) {
        Item u = em.find(Item.class, id);

        if (u != null) {
            System.out.println("FIND COMPLETE: " + u);
        }

        return u;
    }
    
    @Override
    public boolean sellItem(String name, String description, int sp, LocalDateTime ed, Long uid, String category) {
        AppUser u = em.find(AppUser.class, uid);
        
        if(u != null) {
            return sellItem(u, new Item(name, description, sp, ed, u, category));
        }
        
        return false;
    }
    
    @Override
    public boolean sellItem(AppUser u, Item i) {
        if(i.getEndDate().compareTo(LocalDateTime.now()) > 0) {
            u.getItems().add(i);
            i.setOwner(u);

            em.persist(i);
            em.merge(u);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean bidOnItem(Long iid, Long uid, int bid) {
        Item i = findItem(iid);
        AppUser u = em.find(AppUser.class, uid);
        
        if(u != null && i != null && i.getEndDate().compareTo(LocalDateTime.now()) >= 0 && i.getBid() < bid && bid >= i.getStartingPrice()) {
            i.setBid(bid);
            i.setBidder(u);
            em.merge(i);
            
            return true;
        }
        
        return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listItems() {
        TypedQuery q = em.createNamedQuery("Item.list", Item.class);

        return q.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listItemsNotEnded() {
        TypedQuery q = em.createNamedQuery("Item.listNotEnded", Item.class);
        q.setParameter("date", LocalDateTime.now());
        return q.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listFilteredByName(String name) {
        TypedQuery q = em.createNamedQuery("Item.filterByName", Item.class);
        q.setParameter("name", name+"%");
    return q.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listFilteredByCategory(String category) {
        TypedQuery q = em.createNamedQuery("Item.filterByCategory", Item.class);
        q.setParameter("category", category+"%");
        return q.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listUserItemsSelling(Long currentUser) {
        TypedQuery q = em.createNamedQuery("Item.listItemsBySeller", Item.class);
        q.setParameter("id", currentUser);
        return q.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Item> listUserItemsByBuyer(Long currentUser) {
        TypedQuery q = em.createNamedQuery("Item.listItemsByBuyer", Item.class);
        q.setParameter("id", currentUser);
        return q.getResultList();
    }
}
