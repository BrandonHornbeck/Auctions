/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.ItemManager;
import fr.univlorraine.auctions.pages.utility.Session;
import fr.univlorraine.auctions.entities.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private ItemManager im;
    
    private List<Item> itemList;
    private List<Item> itemListSelling;
    private List<Item> itemListPrevious;
    
    private String name;
    private String category;
    
    
    /**
     * Creates a new instance of ListItems
     */
    public ListItems() {
        itemList = new ArrayList();
    }
    
    @PostConstruct
    public void post() {
        itemList = im.listItemsNotEnded();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Item> getItemList() {
        if((name == null || name.trim().isEmpty()) && (category == null ||
                category.trim().isEmpty())) {
            itemList = im.listItemsNotEnded();
        }
        else if(name != null && !name.trim().isEmpty()) {
            filterByName();
        }
        else if(category != null && ! category.trim().isEmpty()) {
            filterByCategory();
        }
        return itemList;
    }

    public List<Item> getItemListSelling() {
        itemListSelling = im.listUserItemsSelling(session.currentUser());
        return itemListSelling;
    }

    public void setItemListSelling(List<Item> itemListSelling) {
        this.itemListSelling = itemListSelling;
    }
    
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    public void filterByName(){
        this.itemList = this.im.listFilteredByName(this.name.trim());
    }
    
    public void filterByCategory(){
        this.itemList = this.im.listFilteredByCategory(this.category.trim());
    }
    
    public List<Item> getItemListPrevious() {
        itemListPrevious = im.listItemsPrevious();
        System.out.println("previous: " + itemListPrevious.size());
        return itemListPrevious;
    }

    public void setItemListPrevious(List<Item> itemListPrevious) {
        this.itemListPrevious = itemListPrevious;
    }
}
