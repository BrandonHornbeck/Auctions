/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.controller;

import fr.univlorraine.auctions.constants.AppConfig;
import fr.univlorraine.auctions.entities.Auction;
import fr.univlorraine.auctions.entities.Delivery;
import fr.univlorraine.auctions.entities.Item;
import fr.univlorraine.auctions.entities.Orders;
import fr.univlorraine.auctions.entities.Paymentmethod;
import fr.univlorraine.auctions.entities.Transactions;
import fr.univlorraine.auctions.model.AppUserFacadeLocal;
import fr.univlorraine.auctions.model.AuctionFacadeLocal;
import fr.univlorraine.auctions.model.DeliveryFacadeLocal;
import fr.univlorraine.auctions.model.ItemFacadeLocal;
import fr.univlorraine.auctions.model.OrdersFacadeLocal;
import fr.univlorraine.auctions.model.TransactionsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author awais
 */
@Named("orderController")
@SessionScoped
public class OrderController implements Serializable{
    
    @EJB
    private OrdersFacadeLocal ordersFacade;
    
    @EJB
    private AuctionFacadeLocal auctionFacade;
    
    @EJB
    private ItemFacadeLocal productFacade;
    
    @EJB
    private TransactionsFacadeLocal transactionFacade;
    
    @EJB
    private DeliveryFacadeLocal deliveryFacade;
    
    List<Auction> listOfAuctionProducts;
    
    String newAddress;
    Paymentmethod newPayment = new Paymentmethod();
    
    @EJB
    AppUserFacadeLocal userFacede;
    
    List<Item> orderedProducts;
    
    public List<Orders> getOrderList(){
        List<Orders> orderList = ordersFacade.findByBuyerId(AppConfig.getInstance().getUserID());
        System.out.println("orderList");
        System.out.println(orderList);
        System.out.println(orderList.get(0).getStatus());
        orderedProducts = new ArrayList<>();
        List<Integer> arrayProdId = new ArrayList<>();
        
        for (int i = 0; i<orderList.size(); i++){   
            int auxProdId = orderList.get(i).getProductId();
            arrayProdId.add(auxProdId);
        }
        
        if(!arrayProdId.isEmpty()){
            orderedProducts = productFacade.findByArrayId(arrayProdId);
        }
       
        return orderList;
    }
    
    public String getProductName(int productId){
         for(int i = 0; i < orderedProducts.size(); i++){
             Long currentProdId = orderedProducts.get(i).getId();
            if(currentProdId == productId){
               return orderedProducts.get(i).getName();
            }
        }
        return null;
    }
    
    public String getProductDescription(int productId){
        
         for(int i = 0; i < orderedProducts.size(); i++){
             Long currentProdId = orderedProducts.get(i).getId();
            if(currentProdId == productId){
               return orderedProducts.get(i).getDescription();
            }
        }
        return null;
    }
    
    public String placeOrder(){
        
        for(int i = 0; i < listOfAuctionProducts.size(); ++i){
            Orders order = new Orders();
            order.setBuyerId(AppConfig.getInstance().getUserID());
            order.setAuctionId(listOfAuctionProducts.get(i).getId());
            order.setProductId(listOfAuctionProducts.get(i).getProductId());
            
            listOfAuctionProducts.get(i).setStatus(3);
            order.setStatus(listOfAuctionProducts.get(i).getStatus()); //Processing
            
            ordersFacade.create(order);
                     
            
            //create transaction
            Transactions tran;
            tran = new Transactions();
            tran.setAmount(listOfAuctionProducts.get(i).getPrice().toString());
            tran.setUserId(listOfAuctionProducts.get(i).getUserId());
            tran.setDescription("Product :"+listOfAuctionProducts.get(i).getProductId());
            tran.setStatus(0);
            transactionFacade.create(tran);
            
            
            //create delivery
            Delivery del = new Delivery();
            del.setUserId(listOfAuctionProducts.get(i).getUserId());
            del.setProductName(listOfAuctionProducts.get(i).getProductId());
            del.setStatus(0);
            del.setCreatedAt(new Date());
            del.setOrderid(order.getId());
            deliveryFacade.create(del);
            
            
            
            //Update Status of products in auction     
            auctionFacade.edit(listOfAuctionProducts.get(i));
            
        }
        auctionFacade.callFlush();
        return "/processingOrder.xhtml?faces-redirect=true";    
    }
    
    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public Paymentmethod getNewPayment() {
        return newPayment;
    }
    

    public void setNewPayment(Paymentmethod newPayment) {
        this.newPayment = newPayment;
    }

    public List<Auction> getListOfAuctionProducts() {
        return listOfAuctionProducts;
    }

    public void setListOfAuctionProducts(List<Auction> listOfAuctionProducts) {
        this.listOfAuctionProducts = listOfAuctionProducts;
    }
    
    public String setAuctionProductsList(List<Auction> listOfAuctionProducts){
        this.listOfAuctionProducts = listOfAuctionProducts;
        
        return "/shoppingcartDetails.xhtml?faces-redirect=true"; 
    }

}

