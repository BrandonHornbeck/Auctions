/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.model;

import fr.univlorraine.auctions.entities.AppUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp
 */
@Local
public interface AppUserFacadeLocal {

    void create(AppUser appUser);

    void edit(AppUser appUser);

    void remove(AppUser appUser);

    AppUser find(Object id);

    List<AppUser> findAll();

    List<AppUser> findRange(int[] range);

    int count();
    
}
