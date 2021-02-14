/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.Item;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Named(value = "listItems")
@RequestScoped
public class ListItems {

    @Inject
    private Session session;
    
    @Inject
    private UserManager userManager;
    
    private List<Item> itemList;
    /**
     * Creates a new instance of ListItems
     */
    public ListItems() {
    }

    public List<Item> getItemList() {
        itemList = userManager.listItems();
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    
}
