/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author oz
 */
@Entity
@NamedQuery(name = "Item.list",
        query = "SELECT i FROM Item i")
@NamedQuery(name = "Item.listItemsBySeller",
        query = "SELECT i FROM Item i WHERE i.owner.id = :id AND i.ordered = false")
@NamedQuery(name = "Item.listItemsByBuyer",
        query = "SELECT i FROM Item i WHERE i.bidder.id = :id AND i.ordered = false")
@NamedQuery(name = "Item.listNotEnded",
        query = "SELECT i FROM Item i WHERE i.endDate >= :date AND i.ordered = false")
@NamedQuery(name = "Item.filterByName",
        query = "SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(:name) AND i.ordered = false")
@NamedQuery(name = "Item.filterByCategory",
        query = "SELECT i FROM Item i WHERE LOWER(i.category) LIKE LOWER(:category) AND i.ordered = false") 
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    private int startingPrice; //in cents
    private LocalDateTime endDate; //in seconds

    @ManyToOne
    private AppUser owner;
    
    @ManyToOne
    private AppUser bidder;
    private int bid;

    private String category;
    
    private boolean ordered = false;
    
    public Item() {
        bid = -1;
        bidder = null;
    }

    public Item(String name, String description, int startingPrice, 
            LocalDateTime endDate, AppUser owner, String category) {
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        this.endDate = endDate;
        this.owner = owner;
        this.category = category;
        
        bid = -1;
        bidder = null;
    }

    public boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public AppUser getBidder() {
        return bidder;
    }

    public void setBidder(AppUser bidder) {
        this.bidder = bidder;
    }

    public int getBid() {
        return bid;
    }
    
    public double getPrice() {
        return Math.max(bid, startingPrice) / 100.0;
    }

    public void setBid(int bid) {
        this.bid = bid;
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

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", description=" + description + ", startingPrice=" + startingPrice + ", endDate=" + endDate + ", owner=" + owner + '}';
    }
}
