/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.constants;

import fr.univlorraine.auctions.entities.AppUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author awais
 */
public class AppConfig {

    private List<String> category;

    private Map<String, List<String>> subCategory;

    private static AppConfig instance;

    public static AppConfig getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new AppConfig();
            return instance;
        }
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Map<String, List<String>> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Map<String, List<String>> subCategory) {
        this.subCategory = subCategory;
    }

    public AppUser getUserSession() {
        context = FacesContext.getCurrentInstance();
        return (AppUser) context.getExternalContext().getSessionMap().get("user");
    }

    public Long getUserID() {

        return getUserSession().getId();
    }

    FacesContext context;

    public AppConfig() {

        category = new ArrayList<>();
        subCategory = new HashMap<>();

        category.add("Mobile");
        category.add("Car");

        List<String> sub1 = new ArrayList<>();
        sub1.add("nokia");
        sub1.add("smasung");

        List<String> sub2 = new ArrayList<>();
        sub2.add("toyota");
        sub2.add("honda");

        subCategory.put("Mobile", sub1);

        subCategory.put("Car", sub2);
    }

}

