/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.pages.utility.Session;
import fr.univlorraine.auctions.pages.utility.UrlManager;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Named(value = "logOut")
@RequestScoped
public class LogOut {

    @Inject
    private Session session;
    
    @Inject
    private UrlManager url;
    
    /**
     * Creates a new instance of LogOut
     */
    public LogOut() {
    }
    
    
   public String logOut() {
       session.logOut();
       return url.home();
   }
}
