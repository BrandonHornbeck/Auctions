/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import fr.univlorraine.auctions.beans.session.ClientInfo;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author oz
 */
@RequestScoped
public class Session {
    private Map<String, Object> sessionMap;
    
    @Inject
    private ClientInfo clientInfo;

    public Session() {
    }
    
    @PostConstruct
    public void init() {
        sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
    
    public boolean isConnected() {
        return sessionMap.containsKey(SessionKeys.USER);
    }
    
    public String currentUser() {
        return (String) sessionMap.get(SessionKeys.USER);
    }
    
    public boolean logIn(String login, String passwd) {
        if(! isConnected() && clientInfo.login(login, passwd)) {
            sessionMap.put(SessionKeys.USER, login);
            return true;
        }
        return false;
    }
    
    public boolean logOut() {
        sessionMap.remove(SessionKeys.USER);
        return true;
    }
}
