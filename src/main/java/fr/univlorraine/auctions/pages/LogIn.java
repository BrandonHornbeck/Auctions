/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.UserManager;
import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author oz
 */
@Named(value = "login")
@Stateless
public class LogIn {

    private String login;
    private String passwd;

    private String status;

    private String name;
    private String description;

    private String startingPrice;
    private String endDate;

    private AppUser currentUser = null;
    private List<Item> itemList;

    @Inject
    private UserManager userManager;

    public LogIn() {
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String login() {
        List<AppUser> l = userManager.findByLogin(login);

        for (AppUser u : l) {
            if (u.getLogin().equals(login) && u.getPasswd().equals(passwd)) {
                status = "logged in";
                currentUser = u;
                updateItemList();
                return "loggedin";
            }
        }

        status = "failed: user not found";
        return "login";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String sell() {
        //public Item(String name, String description, int startingPrice, long endDate, AppUser owner) {

        int sp = (int) (Double.parseDouble(startingPrice) * 100);

        LocalDateTime ed = LocalDateTime.parse(endDate);

        Item i = new Item(name, description, sp, ed, currentUser);

        userManager.sellItem(currentUser, i);

        updateItemList();
        return "loggedin";
    }
}
