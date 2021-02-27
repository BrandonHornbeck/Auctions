/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Item;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author oz
 */
@Local
public interface ItemManager {
    public Item findItem(Long id);
    
    public boolean sellItem(String name, String description, int sp, LocalDateTime ed, Long uid, String category);
    
    public boolean sellItem(AppUser u, Item i);
    
    public boolean removeItem(Long iid);
    
    public boolean bidOnItem(Long iid, Long uid, int bid);
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listItems();
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listItemsNotEnded();
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listFilteredByName(String name);
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listFilteredByCategory(String category);
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listUserItemsSelling(Long currentUser);
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> listUserItemsByBuyer(Long currentUser);
}
