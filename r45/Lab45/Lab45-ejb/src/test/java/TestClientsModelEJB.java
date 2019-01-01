
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.polsl.Client;
import pl.polsl.ClientsModelEJB;

public class TestClientsModelEJB {

    private static Properties properties;
    private static ClientsModelEJB model;

    @BeforeClass
    public static void init() {
        properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.openejb.client.LocalInitialContextFactory");
        properties.put("Lab-PU",
                "new://Resource?type=DataSource");
        properties.put("Lab-PU.UserName", "lab");
        properties.put("Lab-PU.Password", "lab");
        properties.put("Lab-PU.JtaManaged", "true");
        properties.put("Lab-PU.JdbcDriver",
                "com.mysql.jdbc.Driver");
        properties.put("Lab-PU.JdbcUrl",
                "jdbc:mysql://localhost/lab?characterEncoding=utf8");
        try {
            model = (ClientsModelEJB) new InitialContext(properties).
                    lookup("ClientsModelEJBLocalBean");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    @Before
    public void setup() {
        for (int i = 0; i < 5; i++) {
            model.createClient("Client " + i);
        }
    }

    @Test
    public void testCreate() {
        assertEquals("Should be 5 Clients", 5,
                model.findAllClients().size());
        model.createClient("Tomasz");
        assertEquals("Should be 6 Clients", 6,
                model.findAllClients().size());
        assertTrue("Should be Tomasz",
                model.findAllClients()
                        .get(5).getName().equals("Tomasz"));
    }

    @Test
    public void testFindAll() {
        assertEquals("Should be 5 Clients", 5,
                model.findAllClients().size());
    }

    @Test
    public void testRemove() {
        boolean removed;
        assertEquals("Should be 5 Clients", 5,
                model.findAllClients().size());
        removed = model.removeClient(model.findAllClients().get(0).getId());
        assertEquals("Should be 4 Clients", 4,
                model.findAllClients().size());
        assertTrue("Should be true", removed);
        removed = model.removeClient(0);
        assertEquals("Should be 4 Clients", 4,
                model.findAllClients().size());
        assertFalse("Should be true", removed);
    }

    @After
    public void destroy() {
        for (Client c : model.findAllClients()) {
            model.removeClient(c.getId());
        }
    }
}
