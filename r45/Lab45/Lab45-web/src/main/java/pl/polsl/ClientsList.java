/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aspire
 */
public class ClientsList implements Serializable{
    private List<Client> clients;
    private static final ClientsList instance = new ClientsList();
    @EJB
    private ClientBeanLocal clientBean;
    private ClientsList(){
        clients = clientBean.listClients();
        /*for(int i = 0; i < 5; i++){
            clients.add(new Client("Client" + i));
        }*/
    }
    public static ClientsList getInstance(){
        return instance;
    }

    public List<Client> getClients() {
        return clients;
    }
}
