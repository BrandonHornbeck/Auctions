/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.managers.ItemManager;
import fr.univlorraine.auctions.pages.utility.Session;
import fr.univlorraine.auctions.pages.utility.UrlManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@Named(value = "sellItem")
@RequestScoped
public class SellItem {

    @Inject
    private Session session;
    
    @Inject
    private ItemManager im;
    
    @Inject
    private UrlManager url;
    
    private String name;
    private String description;
    private String startingPrice;
    private String endDate;
    private String category;
    private String status;
    
    /**
     * Creates a new instance of SellItem
     */
    public SellItem() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
    public String sell() {
        if(session.isConnected()) {
            int sp = (int) (Double.parseDouble(startingPrice) * 100);
            
            //LocalDateTime ed = LocalDate.parse(endDate, ISO_LOCAL_DATE).atStartOfDay();
            LocalDateTime ed = LocalDateTime.parse(endDate);
            
            if(im.sellItem(name, description, sp, ed, session.currentUser(), category)) {
                status = "success";
            }
            else {
                status = "fail";
            }
        }
        return url.sell();
    }
    
    public String remove(String id) {
        if(session.isConnected()) {
            Long itemId = Long.parseLong(id);
        
            im.removeItem(itemId);
            
            return url.sell();
        }
        
        return url.home();
    }
}
