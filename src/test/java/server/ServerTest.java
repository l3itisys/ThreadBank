package server;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServerTest {

    private WebServer server;
    private AccountManager accountManager;

    @Before
    public void setUp() {
        accountManager = new AccountManager();
        server = new WebServer(accountManager);
    }

    @Test
    public void testServerInitialization() {
        // Testing the server initialization
        assertNotNull("Server should not be null", server);
    }

    @Test
    public void testAccountManagerIntegration() {
        // Testing the integration of AccountManager with the server
        assertNotNull("AccountManager in server should not be null", server.getAccountManager());
        assertEquals("AccountManager should be the same as the one in server", accountManager, server.getAccountManager());
    }

}

