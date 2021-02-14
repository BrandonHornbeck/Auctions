/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.session;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.AppUser;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Stateless
public class ClientInfoLocal implements ClientInfo, Serializable {
    
    @Inject
    private UserManager um;

    public ClientInfoLocal() {
    }
    
    @Override
    public boolean login(String login, String password) {
        for(AppUser u : um.findByLogin(login)) {
            if(u.getLogin().equals(login) && u.getPasswd().equals(password)) {
                return true;
            }
        }
        
        return false;
    }
}
