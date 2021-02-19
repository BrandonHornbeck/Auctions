/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "PAYMENTMETHOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentmethod.findAll", query = "SELECT p FROM Paymentmethod p"),
    @NamedQuery(name = "Paymentmethod.findById", query = "SELECT p FROM Paymentmethod p WHERE p.id = :id"),
    @NamedQuery(name = "Paymentmethod.findByName", query = "SELECT p FROM Paymentmethod p WHERE p.name = :name"),
    @NamedQuery(name = "Paymentmethod.findByNum", query = "SELECT p FROM Paymentmethod p WHERE p.num = :num"),
    @NamedQuery(name = "Paymentmethod.findByExpiredate", query = "SELECT p FROM Paymentmethod p WHERE p.expiredate = :expiredate"),
    @NamedQuery(name = "Paymentmethod.findByUserid", query = "SELECT p FROM Paymentmethod p WHERE p.userid = :userid"),
    @NamedQuery(name = "Paymentmethod.findByCcv", query = "SELECT p FROM Paymentmethod p WHERE p.ccv = :ccv")})
public class Paymentmethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "NUM")
    private String num;
    @Size(max = 100)
    @Column(name = "EXPIREDATE")
    private String expiredate;
    @Column(name = "USERID")
    private Integer userid;
    @Column(name = "CCV")
    private Integer ccv;

    public Paymentmethod() {
    }

    public Paymentmethod(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCcv() {
        return ccv;
    }

    public void setCcv(Integer ccv) {
        this.ccv = ccv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentmethod)) {
            return false;
        }
        Paymentmethod other = (Paymentmethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.univlorraine.auctions.entities.Paymentmethod[ id=" + id + " ]";
    }
    
}
