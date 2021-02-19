/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.controller;

import fr.univlorraine.auctions.constants.AppConfig;
import fr.univlorraine.auctions.entities.AppUser;
import fr.univlorraine.auctions.entities.Auction;
import fr.univlorraine.auctions.entities.Item;
import fr.univlorraine.auctions.model.AppUserFacadeLocal;
import fr.univlorraine.auctions.model.AuctionFacadeLocal;
import fr.univlorraine.auctions.model.ItemFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named("auctionController")
@SessionScoped
/**
 *
 * @author awais
 */
public class AuctionController implements Serializable {
    @EJB
    private AuctionFacadeLocal auctionFacade;
    @EJB
    private ItemFacadeLocal productFacade;
    @EJB
    private AppUserFacadeLocal userFacade;        
    List<Item> productList;
    
    List<Auction> selectedAuctionItems;
    double bidPrice;
    private List<Item> ItemList;
    
    public List<Auction> getAuction(){
        List<Auction> auctionList=auctionFacade.findByUserId(AppConfig.getInstance().getUserID());
        List<Integer> arrayProdId= new ArrayList<>();
        Iterator<Auction> i = auctionList.iterator();
        while (i.hasNext()) {
           Auction currentAuc = i.next();
           if(currentAuc.getStatus() == 3){
               i.remove();
           }else{
               int b=currentAuc.getProductId();
                arrayProdId.add(b);
           } 
        }

        if(!arrayProdId.isEmpty()){
            productList = productFacade.findByArrayId(arrayProdId);
        }
                  
        return auctionList;
    }
    
    public String remove(Auction auction){

        auctionFacade.remove(auction);
        if(auction.getStatus()!=0){
            AppUser u = new AppUser();
            u = userFacade.find(auction.getUserId());
            u.setWithdrawnPenalty(u.getWithdrawnPenalty()+1);
            if(u.getWithdrawnPenalty()==2){
                Date d = new Date();
                Calendar c= Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.DATE, 15);
                d=c.getTime();
                u.setWithdrawnPenaltyDate(d);
                u.setWithdrawnPenalty(0);
               
            
            }
            userFacade.edit(u);
        }
        return "/viewAuction.xhtml?faces-redirect=true";
    
    }
    
    
    
    public String getProductName(int productId){
         for(int i=0; i<productList.size(); i++){
             Long b=productList.get(i).getId();
            if(b==productId){
               return productList.get(i).getName();
            }
        }
        return null;
    }
    
    public String getProductDescription(int productId){
        
         for(int i = 0; i<productList.size(); i++){
             Long currentProdId = productList.get(i).getId();
            if(currentProdId == productId){
               return productList.get(i).getDescription();
            }
        }
        return null;
    }
    
    public Double getHighestBid(int prodId){
        
        Double value = 0.0;
        
        if(auctionFacade.getHighestBid(prodId) != null){
            value = (Double) auctionFacade.getHighestBid(prodId);
        }
        
        return value;
    }

    public List<Auction> getSelectedItems() {
        return selectedAuctionItems;
    }

    public void setSelectedItems(List<Auction> selectedItems) {
        this.selectedAuctionItems = selectedItems;
    }

    public List<Item> getProductList() {
        return ItemList;
    }

    public void setProductList(List<Item> productList) {
        this.productList = productList;
    }

    public List<Auction> getSelectedAuctionItems() {
        return selectedAuctionItems;
    }

    public void setSelectedAuctionItems(List<Auction> selectedAuctionItems) {
        this.selectedAuctionItems = selectedAuctionItems;
    }
    
    public String createBid(int productId){
        Auction auction = new Auction();
        auction.setCreatedOn(new Date());
        auction.setPrice(bidPrice);
        auction.setProductId(productId);
        auction.setUserId(AppConfig.getInstance().getUserID());
        auction.setStatus(0);
        auctionFacade.create(auction);
        //return "/viewAuction.xhtml?faces-redirect=true";
        return "/bidCreated.xhtml?faces-redirect=true";
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
    
    
}

