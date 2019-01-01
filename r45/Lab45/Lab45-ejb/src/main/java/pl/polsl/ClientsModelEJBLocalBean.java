/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.util.List;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
public interface ClientsModelEJBLocalBean {
    public List<Client> findAllClients();
    public boolean removeClient(Integer id);
    public void createClient(String name);
}
