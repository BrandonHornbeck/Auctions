/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author oz
 */


@Stateless
public class UserManagerLocal implements UserManager {
    @PersistenceContext(unitName = "Auctions-libPU")
    private EntityManager em;
    
    @Override
    public AppUser findUser(Long id) {
        AppUser u = em.find(AppUser.class, id);

        if (u != null) {
            System.out.println("FIND COMPLETE: " + u);
        }

        return u;
    }
    
    @Override
    public List<AppUser> findByLogin(String login) {
        TypedQuery q = em.createNamedQuery("AppUser.findByLogin", AppUser.class);
        q.setParameter("login", login);

        return q.getResultList();
    }
    
    @Override
    public AppUser getByLogin(String login) {
        List<AppUser> r = findByLogin(login);
        
        if(r.isEmpty()) {
            return null;
        }
        return r.get(0);
    }
    
    @Override
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
}
