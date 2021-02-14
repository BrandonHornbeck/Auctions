/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.pages;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author oz
 */
@Named(value = "login")
@RequestScoped
public class LogIn implements Serializable {

    private String login;
    private String passwd;

    private String status;

    @Inject
    private Session session;
    
    public LogIn() {
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String login() {
        
        if(session.logIn(login, passwd)) {
            status = "logged in";
            return "loggedin";
        }

        status = "failed: user not found";
        return "login";
    }

    public String getStatus() {
        if(session.isConnected()) {
            status = "connected";
        }
        else {
            status = "not connected";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
