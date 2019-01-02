package pl.polsl;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientsModelEJB implements ClientsModelEJBLocalBean{

    @PersistenceContext
    private EntityManager em;
    
    public void createClient(String name) {
	em.persist(new Client(name));
    }
    
    public List<Client> findAllClients() {
	return em.createQuery("SELECT s FROM Client s").
		getResultList();
    }
    
    public boolean removeClient(Integer id) {
	Client client = em.find(Client.class, id);
	if(client != null) {
	    em.remove(client);
	    return true;
	}
	return false;
    }
}
