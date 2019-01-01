/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.polsl.Client;

/**
 * A purchaseName
 * @version 1.0
 * @author Adam Gajewski
 */
@Entity
@Table(name = "my_purchase")
public class Purchase implements Serializable{
    /**
     * purchaseName id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToOne(mappedBy = "user",cascade = CascadeType.PERSIST)
    private Integer id;
    /**
     * Purchase name
     */
    @Column(name = "purchase")
    private String purchaseName;
    /**
     * client that made the purchase
     */
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * returns the client that made the purchaseName
     * @return client
     */
    public Client getCilent() {
        return client;
    }
    /**
     * sets client
     * @param client 
     */
    public void setClient(Client client) {
        this.client = client;
    }
    /**
     * returns the name of purchaseName
     * @return purchaseName
     */
    public String getPurchaseName() {
        return purchaseName;
    }
    /**
     * sets purchaseName
     * @param purchaseName 
     */
    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }
    /**
     * returns id of the purchaseName
     * @return purchaseName id
     */
    public Integer getId() {
        return id;
    }
    /**
     * sets purchaseName id
     * @param id - id if the purchaseName
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * generates hash code
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.purchaseName);
        hash = 23 * hash + Objects.hashCode(this.client);
        return hash;
    }
    /**
     * compares two objects
     * @param obj compared object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (!Objects.equals(this.purchaseName, other.purchaseName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }
    
}
