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
public class PurchaseBean implements PurchaseBeanLocal {
    /**
     * EntityManager
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * creates or updates purchase
     * @param purchase purchase
     * @return purchase
     */
    public Purchase createOrUpdatePurchase(Purchase purchase){
        if(purchase.getId() == null){
            em.persist(purchase);
        }else{
            em.merge(purchase);
        }
        return purchase;
    }
    /**
     * returns list of Purchases
     * @return list of Purchases
     */
    public List<Purchase> listPurchases(){
        List<Purchase> list = em.createQuery(
            "SELECT p FROM Purchase p").getResultList();
        return list;
    }
    /**
     * removes purchase
     * @param id purchase id
     */
    public void removePurchase(int id){
        Purchase c = findPurchase(id);
        if(c != null)
            em.remove(c);
    }
    /**
     * returnd purchase
     * @param id purchase id
     * @return purchase
     */
    public Purchase findPurchase(int id){
        return em.find(Purchase.class, id);
    }
}
