/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.beans.session.ClientInfo;
import fr.univlorraine.auctions.entities.Item;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author oz
 */
@Named(value = "login")
@RequestScoped
public class LogIn {

    private String login;
    private String passwd;

    private String status;
    
    private String name;
    private String description;

    private String startingPrice;
    private String endDate;
    
    private List<Item> itemList;

    @EJB
    private ClientInfo clientInfo;
    
    @Inject
    private UserManager userManager;
    
    public LogIn() {
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String login() {
        
        if(clientInfo.login(login, passwd)) {
            status = "logged in";
            return "loggedin";
        }

        status = "failed: user not found";
        return "login";
    }

    public String getStatus() {
        if(clientInfo.isConnected()) {
            status = "connected: " + clientInfo.currentUser().getLogin();
        }
        else {
            status = "not connected";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    private void updateItemList() {
        itemList = userManager.listItems();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    public String sell() {
        //public Item(String name, String description, int startingPrice, long endDate, AppUser owner) {

        if(clientInfo.isConnected()) {
            int sp = (int) (Double.parseDouble(startingPrice) * 100);

            LocalDateTime ed = LocalDateTime.parse(endDate);

            Item i = new Item(name, description, sp, ed, clientInfo.currentUser());

            userManager.sellItem(clientInfo.currentUser(), i);

            updateItemList();
        }
        return "loggedin";
    }
}
