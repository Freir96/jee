/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import pl.polsl.ClientType;


/**
 * A client
 * @version 1.1
 * @author Adam Gajewski
 */
@Entity
@Table(name = "my_client")
@NamedQueries({@NamedQuery(name="Client.findAll", query = "SELECT s FROM Client s"),
@NamedQuery(name="Client.findByName", 
        query = "SELECT s FROM Client s WHERE s.name LIKE :name ORDER BY s.clientType")})
public class Client implements Serializable {
    /**
     * client id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * client name
     */
    @Column(name = "client_name", length = 50, nullable = false)
    private String name;
    /**
     * type of the client
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

//    @Transient
//    private String firstName;
//    @Transient
//    private String lastName;
    /**
     * Defoult constructor.
     */
    public Client() {
    }
    /**
     * Constructor with parameters.
     * @param name - name of the client
     * 
     */
    public Client(String name) {
        this.name = name;
        this.clientType=ClientType.PREMIUM;
    }
    /**
     * Returns clientType
     * @return type of client
     */
    public ClientType getClientType() {
        return clientType;
    }
    /**
     * sets type of client
     * @param clientType - type of client
     */
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
    /**
     * returns name
     * @return clients name
     */
    public String getName() {
        return name;
    }
    /**
     * sets name
     * @param name - clients name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }
    /**
     * returns id
     * @return clients id
     */
    public Integer getId() {
        return id;
    }
    /**
     * sets id
     * @param id clients id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
        public String toString(){
    return new StringBuilder().append(id).append(", ").append(name).append(", ").append(clientType).toString();
    
}

}
