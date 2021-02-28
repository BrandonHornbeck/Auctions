package fr.univlorraine.auctions.pages.utility;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author oz
 */
@Named(value = "url")
@SessionScoped
public class UrlManager implements Serializable {

    public String home() {
        return "index";
    }

    public String login() {
        return "login";
    }

    public String sell() {
        return "sell";
    }

    public String filter() {
        return "filter";
    }
    
    public String buy() {
        return "buy";
    }
}
