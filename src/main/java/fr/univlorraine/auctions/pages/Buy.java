/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import fr.univlorraine.auctions.pages.utility.Session;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Named(value = "buy")
@RequestScoped
public class Buy {
    
    @Inject
    private Session session;
    
    @Inject
    private UserManager userManager;
    
    private List<Item> cart;
    private List<Item> items;
    
    private String creditCardNumber;
    private String address;
    
    /**
     * Creates a new instance of Bid
     */
    public Buy() {
        cart = new ArrayList<>();
    }
    
    public String addToCart(String iid) {
        Long itemId = Long.parseLong(iid);
        Long uid = session.currentUser();
        
        boolean res = userManager.addItemToCart(itemId, uid);
        System.out.println("addToCart: " + uid + " -> " + iid + " : " + res);
        
        return "buy";
    }
    
    public String removeFromCart(String iid) {
        Long itemId = Long.parseLong(iid);
        Long uid = session.currentUser();
        
        boolean res = userManager.removeFromCart(itemId, uid);
        System.out.println("removeFromCart: " + uid + " -> " + iid + " : " + res);
        
        return "buy";
    }

    public List<Item> getCart() {
        cart.clear();
        cart.addAll(userManager.findUser(session.currentUser()).getCart());
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public List<Item> getItems() {
        items = userManager.listUserItemsByBuyer(session.currentUser());
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String order() {   
        if(! creditCardNumber.isEmpty() && !address.isEmpty()) {
            userManager.orderCart(session.currentUser(), creditCardNumber, address);
        }
        
        return "buy";
    }
}
