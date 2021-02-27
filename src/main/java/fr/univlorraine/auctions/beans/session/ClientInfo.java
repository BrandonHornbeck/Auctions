/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.beans.session;

import javax.ejb.Local;

/**
 *
 * @author oz
 */
@Local
public interface ClientInfo {
  Long login(String username, String password);
}
