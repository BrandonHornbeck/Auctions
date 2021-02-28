/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.managers;

import fr.univlorraine.auctions.entities.AppUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author oz
 */
@Local
public interface UserManager{
    public AppUser findUser(Long id);
    public List<AppUser> findByLogin(String login);
    public AppUser getByLogin(String login);
    public Long addUser(AppUser u);
}
