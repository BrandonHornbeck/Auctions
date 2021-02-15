/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages.utility;

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
    private final static String USER = "session_user";
    
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
        return sessionMap.containsKey(USER);
    }
    
    public Long currentUser() {
        return (Long) sessionMap.get(USER);
    }
    
    public boolean logIn(String login, String passwd) {
        Long id = clientInfo.login(login, passwd);
        if(! isConnected() && id != -1L) {
            sessionMap.put(USER, id);
            return true;
        }
        return false;
    }
    
    public boolean logOut() {
        sessionMap.remove(USER);
        return true;
    }
}
