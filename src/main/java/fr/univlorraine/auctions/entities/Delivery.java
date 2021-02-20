/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlorraine.auctions.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DELIVERY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findById", query = "SELECT d FROM Delivery d WHERE d.id = :id"),
    @NamedQuery(name = "Delivery.findByCreatedat", query = "SELECT d FROM Delivery d WHERE d.createdat = :createdat"),
    @NamedQuery(name = "Delivery.findByOrderid", query = "SELECT d FROM Delivery d WHERE d.orderid = :orderid"),
    @NamedQuery(name = "Delivery.findByProductname", query = "SELECT d FROM Delivery d WHERE d.productname = :productname"),
    @NamedQuery(name = "Delivery.findByUserid", query = "SELECT d FROM Delivery d WHERE d.userid = :userid"),
    @NamedQuery(name = "Delivery.findByStatus", query = "SELECT d FROM Delivery d WHERE d.status = :status")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERID")
    private int orderid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTNAME")
    private int productname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;

    public Delivery() {
    }

    public Delivery(Integer id) {
        this.id = id;
    }

    public Delivery(Integer id, int orderid, int productname, int userid, int status) {
        this.id = id;
        this.orderid = orderid;
        this.productname = productname;
        this.userid = userid;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductname() {
        return productname;
    }

    public void setProductname(int productname) {
        this.productname = productname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.univlorraine.auctions.entities.Delivery[ id=" + id + " ]";
    }

    public void setUserId(Object userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setProductName(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCreatedAt(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
