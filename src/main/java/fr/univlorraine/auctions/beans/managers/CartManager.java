/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import javax.ejb.Local;

/**
 *
 * @author oz
 */
@Local
public interface CartManager {
    public boolean addItemToCart(Long iid, Long uid);
    
    public boolean removeFromCart(Long iid, Long uid);
    
    public boolean orderCart(Long uid, String ccn, String address);
}
