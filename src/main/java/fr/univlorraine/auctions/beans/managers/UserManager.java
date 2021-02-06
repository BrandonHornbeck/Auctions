/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        if (findByLogin(u.getLogin()).size() != 0) {
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

    public List<AppUser> findByLogin(String login) {
        //TypedQuery<AppUser> q = em.createName

        Query q = em.createNamedQuery("AppUser.findByLogin");
        q.setParameter("login", login);

        return q.getResultList();
    }

    public List<Item> listItems() {
        Query q = em.createNamedQuery("Item.list");

        return q.getResultList();
    }

    public void sellItem(AppUser u, Item i) {
        u.getItems().add(i);
        i.setOwner(u);

        em.persist(i);
        em.merge(u);
    }
}
