/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aspire
 */
@ViewScoped
@ManagedBean
public class ComponentControler implements Serializable{
    private String selectOne;
    private List<String> examples;
    private Client client;
    private Client selectOneClient;
    
    public List<Client> getClients(){
        return ClientsList.getInstance().getClients();
    }
    
    public Client getSelectOneClient() {
        return selectOneClient;
    }

    public void setSelectOneClient(Client selectOneClient) {
        this.selectOneClient = selectOneClient;
    }
    
    @PostConstruct
    protected void init(){
        examples = Arrays.asList("Example 1", "Example 2", 
                "Example 3", "Example 4");
    }
    
    public void delete(int idx){
        getClients().remove(idx);
    }
    
    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public String getSelectOne() {
        return selectOne;
    }

    public void setSelectOne(String selectOne) {
        this.selectOne = selectOne;
    }

    public List<String> getExample() {
        return examples;
    }
    public void actionListener(){
        System.out.println("Selected:" + selectOneClient);
        FacesContext.getCurrentInstance().addMessage("grow1", 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "info", 
                        "Selected value is " + selectOneClient));
    }
}
