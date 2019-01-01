/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
@Stateless
public class ClientBean implements ClientBeanLocal {

    @PersistenceContext
    private EntityManager em;
    /**
     * creates or updates client
     * @param client client
     * @return client
     */
    public Client createOrUpdateClient(Client client){
        if(client.getId() == null){
            em.persist(client);
        }else{
            em.merge(client);
        }
        return client;
    }
    /**
     * returns list of clients
     * @return list of clients
     */
    public List<Client> listClients(){
        List<Client> list = em.createQuery(
            "SELECT c FROM Client c").getResultList();
        return list;
    }
    /**
     * removes client
     * @param id id of client
     */
    public void removeClient(int id){
        Client c = findClient(id);
        if(c != null)
            em.remove(c);
    }
    /**
     * returns client
     * @param id clients id
     * @return client object
     */
    public Client findClient(int id){
        return em.find(Client.class, id);
    }
}
