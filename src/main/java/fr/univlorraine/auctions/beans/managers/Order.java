/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.Item;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author oz
 */
public class Order implements Serializable {
    private String ccn;
    private String addr;
    
    private Long uid;
    private Set<Item> items;

    public Order(String ccn, String addr, Long uid, Set<Item> items) {
        this.ccn = ccn;
        this.addr = addr;
        this.uid = uid;
        this.items = items;
    }

    public Order() {
        items = new HashSet<>();
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" + "ccn=" + ccn + ", addr=" + addr + ", uid=" + uid + ", items=" + items + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.ccn);
        hash = 23 * hash + Objects.hashCode(this.addr);
        hash = 23 * hash + Objects.hashCode(this.uid);
        hash = 23 * hash + Objects.hashCode(this.items);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.ccn, other.ccn)) {
            return false;
        }
        if (!Objects.equals(this.addr, other.addr)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }
}
