/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.util.List;
import javax.ejb.Local;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
@Local
public interface ClientBeanLocal {

    public Client createOrUpdateClient(Client client);

    public List<Client> listClients();
    
    public void removeClient(int id);
    
    public Client findClient(int id);
    
}
