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
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author oz
 */
@Stateless
@LocalBean
public class UserManager {

    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;

    public Long addUser(AppUser u) {
        if (!findByLogin(u.getLogin()).isEmpty()) {
            System.out.println("USER ERROR: login already in DB");
            return -1L;
        }

        for (Item i : u.getItems()) {
            em.persist(i);
        }

        em.persist(u);
        System.out.println("USER: " + u);
        return u.getId();
    }

    public AppUser findUser(Long id) {
        AppUser u = em.find(AppUser.class, id);

        if (u != null) {
            System.out.println("FIND COMPLETE: " + u);
        }

        return u;
    }
    
    public Item findItem(Long id) {
        Item u = em.find(Item.class, id);

        if (u != null) {
            System.out.println("FIND COMPLETE: " + u);
        }

        return u;
    }

    public List<AppUser> findByLogin(String login) {
        TypedQuery q = em.createNamedQuery("AppUser.findByLogin", AppUser.class);
        q.setParameter("login", login);

        return q.getResultList();
    }
    
    public AppUser getByLogin(String login) {
        List<AppUser> r = findByLogin(login);
        
        if(r.isEmpty()) {
            return null;
        }
        return r.get(0);
    }
    
    public boolean sellItem(String name, String description, int sp, LocalDateTime ed, Long uid) {
        AppUser u = findUser(uid);
        
        if(u != null) {
            sellItem(u, new Item(name, description, sp, ed, u));
            
            return true;
        }
        
        return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listItems() {
        TypedQuery q = em.createNamedQuery("Item.list", Item.class);

        return q.getResultList();
    }

    public void sellItem(AppUser u, Item i) {
        u.getItems().add(i);
        i.setOwner(u);

        em.persist(i);
        em.merge(u);
    }
    
    public boolean bidOnItem(Long iid, Long uid, int bid) {
        Item i = findItem(iid);
        AppUser u = findUser(uid);        
        
        if(u != null && i != null && i.getEndDate().compareTo(LocalDateTime.now()) >= 0 && i.getBid() < bid && bid >= i.getStartingPrice()) {
            i.setBid(bid);
            i.setBidder(u);
            em.merge(i);
            
            return true;
        }
        
        return false;
    }
}
