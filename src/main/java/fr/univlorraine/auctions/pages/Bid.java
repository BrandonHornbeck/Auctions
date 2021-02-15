/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.pages.utility.Session;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Named(value = "bid")
@RequestScoped
public class Bid {
    
    @Inject
    private Session session;
    
    @Inject
    private UserManager userManager;
    
    private String bid;
    /**
     * Creates a new instance of Bid
     */
    public Bid() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
    
    public String bid(String iid) {
        Long itemId = Long.parseLong(iid);
        Long uid = session.currentUser();
        int b = (int) (Double.parseDouble(bid) * 100);
        
        boolean res = userManager.bidOnItem(itemId, uid, b);
        System.out.println("bid: " + uid + " -> " + iid + " : " + res);
        
        return "loggedin";
    }
}
