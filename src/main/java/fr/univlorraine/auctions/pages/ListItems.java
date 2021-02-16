/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.pages.utility.Session;
import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.Item;
import java.util.ArrayList;
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
    
    private String name;
    /**
     * Creates a new instance of ListItems
     */
    public ListItems() {
        itemList = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Item> getItemList() {
        if (itemList.isEmpty()){
            itemList = userManager.listItemsNotEnded();
        }
        return itemList;
    }
    
    

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    public void filter(){
        this.itemList = this.userManager.listFilteredByName(this.name);
    }
    
    
}
