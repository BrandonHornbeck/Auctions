/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.model;

import fr.univlorraine.auctions.entities.Auction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp
 */
@Local
public interface AuctionFacadeLocal {

    void create(Auction auction);

    void edit(Auction auction);

    void remove(Auction auction);

    Auction find(Object id);

    List<Auction> findAll();

    List<Auction> findRange(int[] range);

    int count();

    public List<Auction> findByUserId(Long userID);

    public Object getHighestBid(int prodId);

    public void callFlush();
    
}
