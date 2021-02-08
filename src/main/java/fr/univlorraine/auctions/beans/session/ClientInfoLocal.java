/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.session;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.AppUser;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Stateful
public class ClientInfoLocal implements ClientInfo, Serializable {
   
    private AppUser cu;
    private boolean connected;
    
    @Inject
    private UserManager um;

    public ClientInfoLocal() {
        cu = null;
        connected = false;
    }
    
    @Override
    public boolean login(String login, String password) {
        for(AppUser u : um.findByLogin(login)) {
            if(u.getLogin().equals(login) && u.getPasswd().equals(password)) {
                connected = true;
                cu = u;
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public AppUser currentUser() {
        if(isConnected()) {
            return cu;
        }
        return null;
    }
}
